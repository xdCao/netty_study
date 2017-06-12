package ch2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xdcao on 2017/6/12.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port=8080;
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(port);
            System.out.println("The time server is started in port: "+port);
            Socket socket=null;
            while (true){
                socket=serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket!=null){
                System.out.println("The  time server is closed");
                serverSocket.close();
                serverSocket=null;
            }
        }
    }

}
