package ch2;

/**
 * Created by xdcao on 2017/6/12.
 */
public class NIOTimeServer {

    public static void main(String[] args){
        int port=8080;
        MultiplexerTimeServer timeServer=new MultiplexerTimeServer(port);
        new Thread(timeServer).start();
    }

}
