package ch12Nettystack;



import java.io.ObjectOutputStream;

/**
 * Created by xdcao on 2017/6/15.
 */
public final class NettyMessage {

    private Header header;
    private Object body;

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage: [head="+header+"]";
    }
}
