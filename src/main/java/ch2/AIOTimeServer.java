package ch2;

/**
 * Created by xdcao on 2017/6/13.
 */
public class AIOTimeServer {

    public static void main(String[] args){
        int port=8080;
        AsyncTimeServerHandler timeServerHandler=new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler).start();
    }

}
