import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass extends Application {

    public UdpConnectionThread receiveMessagesThread(DatagramSocket datagramSocket,FXMLController controller){
        return new UdpConnectionThread(datagramSocket, controller);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final int PORT = 6666;
        final InetAddress ADDRESS = InetAddress.getByName("192.168.8.255");
        DatagramSocket datagramSocket = new DatagramSocket(PORT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //fxml laduje scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene.fxml"));
        Parent root = fxmlLoader.load();
        FXMLController controller = fxmlLoader.getController();
        controller.setSocket(datagramSocket);
        controller.setAddress(ADDRESS);

        executorService.submit(receiveMessagesThread(datagramSocket, controller));

        //tworzymy glowne okno
        Scene scene = new Scene(root);
        //tytul okna
        stage.setTitle("hell-o");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
