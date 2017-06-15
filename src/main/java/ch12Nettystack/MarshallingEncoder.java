package ch12Nettystack;

import io.netty.buffer.ByteBuf;

import javax.xml.bind.Marshaller;

/**
 * Created by xdcao on 2017/6/15.
 */
public class MarshallingEncoder{

    private static final byte[] LENGTH_PLACEHOLDER=new byte[4];

    Marshaller marshaller;

    public MarshallingEncoder() {
        this.marshaller = (Marshaller) MarshallingCodecFactory.buildMarshallingEncoder();
    }

    public void encode(Object body, ByteBuf sendBuf) {

    }
}
