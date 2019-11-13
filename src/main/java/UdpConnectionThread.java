import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UdpConnectionThread implements Runnable {

    private DatagramSocket datagramSocket;
    private FXMLController controller;

    public UdpConnectionThread(DatagramSocket datagramSocket, FXMLController controller) {
        this.datagramSocket = datagramSocket;
        this.controller=controller;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                this.datagramSocket.receive(packet);
                byte[] data = packet.getData();
                byte[] name = Arrays.copyOfRange(data, 0, 30);
                String stringName = new String(name);
                String stringData = new String(Arrays.copyOfRange(data, 31, 1023));
                controller.addLine("<"+stringName.trim()+">:"+stringData.trim());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
