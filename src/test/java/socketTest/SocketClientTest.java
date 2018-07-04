package testcase.socketTest;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * test
 * Created by xiewen on 2016/9/27.
 */
public class SocketClientTest {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.92",9999));
            if(socket.isConnected()){
                OutputStream outputStream = socket.getOutputStream();
                while (true){
                    String data = "message test";
                    outputStream.write(data.getBytes("utf-8"));
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
