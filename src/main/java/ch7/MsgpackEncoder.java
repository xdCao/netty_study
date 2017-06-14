package ch7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by xdcao on 2017/6/14.
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {


    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack=new MessagePack();
        byte[] raw=messagePack.write(o);
        byteBuf.writeBytes(raw);
    }
}
