package ch7;

import ch6.UserInfo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by xdcao on 2017/6/14.
 */
public class EchoClientHandler extends ChannelHandlerAdapter{

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos=UserInfo();
        for (UserInfo infoE:infos){
            ctx.write(infoE);
        }
        ctx.flush();
    }

    private UserInfo[] UserInfo(){
        UserInfo[] userInfos=new UserInfo[sendNumber];
        UserInfo userInfo=null;
        for (int i=0;i<sendNumber;i++){
            userInfo=new UserInfo();
            userInfo.setUserID(10);
            userInfo.setUserName("caohao");
            userInfos[i]=userInfo;
        }
        return userInfos;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message: "+msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
