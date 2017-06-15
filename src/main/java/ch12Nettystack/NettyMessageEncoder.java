package ch12Nettystack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;


import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by xdcao on 2017/6/15.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder(MarshallingEncoder marshallingEncoder) {
        this.marshallingEncoder = marshallingEncoder;
    }

    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> list) throws Exception {
        if (nettyMessage==null||nettyMessage.getHeader()==null){
            throw new Exception("The encode message is null.");
        }
        ByteBuf sendBuf= Unpooled.buffer();
        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionID());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());
        sendBuf.writeInt(nettyMessage.getHeader().getAttachment().size());
        String key=null;
        byte[] keyArray=null;
        Object value=null;
        for (Map.Entry<String,Object> param:nettyMessage.getHeader().getAttachment().entrySet()){
            key=param.getKey();
            keyArray=key.getBytes("utf-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value=param.getValue();
            marshallingEncoder.encode(value,sendBuf);
        }
        key=null;
        keyArray=null;
        value=null;
        if (nettyMessage.getBody()!=null){
            marshallingEncoder.encode(nettyMessage.getBody(),sendBuf);
        }else {
            sendBuf.writeInt(0);
        }
        sendBuf.setInt(4,sendBuf.readableBytes());
    }
}
