package ch2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xdcao on 2017/6/12.
 */
public class FakeAIOTimeServer {

    public static void main(String[] args) throws IOException {
        int port=8080;
        ServerSocket server=null;
        try {
            server=new ServerSocket(port);
            System.out.println("The time server is started in port : "+port);
            Socket socket=null;
            TimeServerHandlerExecutePool singleExecute=new TimeServerHandlerExecutePool(50,1000);
            while (true){
                socket=server.accept();
                singleExecute.execute(new TimeServerHandler(socket));
            }
        }finally {
            if(server!=null){
                System.out.println("The time server is closed");
                server.close();
                server=null;
            }
        }
    }

}
