import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


public class FXMLController {

    @FXML
    private TextField line;

    @FXML
    private TextArea text;

    @FXML
    private ListView<?> list;

    @FXML
    private Button send;
    private InetAddress address;
    private DatagramSocket datagramSocket;

    public void addLine(String text){
        this.text.setText(this.text.getText().concat(text) + "\n");
    }

    //zrobic liste userow

    @FXML
    public void onBtClick(javafx.event.ActionEvent actionEvent) throws IOException {
        String text = line.getText();
        line.setText("");
//        this.text.appendText(text+"\n");

        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 6666);
        byte[] name = Arrays.copyOf("Sebastian".getBytes(),30);
        byte[] data = Arrays.copyOf(text.getBytes(),1024-30);
        System.arraycopy(name,0,buf,0,30);
        System.arraycopy(data,0,buf,31,993);
        packet.setData(buf);
        datagramSocket.send(packet);
    }

    public void setAddress(InetAddress address) {
        this.address=address;
    }

    public void setSocket(DatagramSocket datagramSocket) {
        this.datagramSocket=datagramSocket;
    }
}
