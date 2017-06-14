package ch3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.logging.Logger;

/**
 * Created by xdcao on 2017/6/13.
 */
public class NettyTimeClientHandler extends ChannelHandlerAdapter{

    private static final Logger logger=Logger.getLogger(NettyTimeClientHandler.class.getName());

    private final ByteBuf firstMsg;


    public NettyTimeClientHandler() {

        byte[] req="QUERY TIME ORDER".getBytes();
        firstMsg= Unpooled.buffer(req.length);
        firstMsg.writeBytes(req);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=(ByteBuf)msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"utf-8");
        System.out.println("Now is: "+body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception: "+cause.getMessage());
        ctx.close();
    }
}
