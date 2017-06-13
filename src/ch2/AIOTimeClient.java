package ch2;

/**
 * Created by xdcao on 2017/6/13.
 */
public class AIOTimeClient {

    public static void main(String[] args){
        int port=8080;
        new Thread(new AsyncTimeClientHandler("127.0.0.1",port)).start();
    }

}
