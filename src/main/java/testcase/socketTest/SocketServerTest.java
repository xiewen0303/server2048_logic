package testcase.socketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * client connection
 * Created by xiewen on 2016/9/26.
 */
public class SocketServerTest {

    public static void main(String[] args) {
        try {
//            Socket client = new Socket("127.0.0.1", 9999);
            ServerSocket server = new ServerSocket(9999);
            System.out.println(server.getSoTimeout());
//            server.setSoTimeout(12);
            while(true){
                final Socket socket = server.accept();
                socket.setSoLinger(true,100);

                System.out.println("accept.......solinger:"+socket.getSoLinger());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            byte[] bytes = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            OutputStream outputStream = socket.getOutputStream();
                            String dataTest = "service return data";

                            int len = 0 ;
                            while((len = inputStream.read(bytes))> 0 ){
                                String data = new String(bytes,0,len,"utf-8");
                                System.out.println(data);
                                outputStream.write(dataTest.getBytes("utf-8"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}