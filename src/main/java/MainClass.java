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


    @Override
    public void start(Stage stage) throws Exception {
        //fxml laduje scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene.fxml"));
        Parent root = fxmlLoader.load();
        FXMLController controller = fxmlLoader.getController();
//        connect(controller);
        //tworzymy glowne okno
        Scene scene = new Scene(root);
        //tytul okna
        stage.setTitle("hell-o");
        stage.setScene(scene);
        stage.show();
    }

    public void connect(FXMLController controller){
        try {
            final int PORT = 6666;
            final InetAddress ADDRESS = InetAddress.getByName("192.168.0.255");
            ExecutorService executorService = Executors.newCachedThreadPool();
            DatagramSocket datagramSocket = new DatagramSocket(PORT);
            UdpConnectionThread udpConnectionThread = new UdpConnectionThread(datagramSocket, controller);
            executorService.submit(udpConnectionThread);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ADDRESS, PORT);
            Scanner scanner = new Scanner(System.in);
            String s = null;
            while ((s = scanner.nextLine())!=null){
                byte[] name = Arrays.copyOf("Sebastian".getBytes(),30);
                byte[] data = Arrays.copyOf(s.getBytes(),1024-30);
                System.arraycopy(name,0,buf,0,30);
                System.arraycopy(data, 0,buf,31, 993);
                packet.setData(buf);
                datagramSocket.send(packet);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
