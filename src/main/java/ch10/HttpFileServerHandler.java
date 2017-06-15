package ch10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.*;


/**
 * Created by xdcao on 2017/6/15.
 * 以后写，api似乎有变动
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<DefaultFullHttpRequest>{

    private String url;

    public HttpFileServerHandler(String url) {
        this.url = url;
    }

    protected void messageReceived(ChannelHandlerContext ctx, DefaultFullHttpRequest request) throws Exception {
        if (!request.decoderResult().isSuccess()){
            sendError(ctx,BAD_REQUEST);
            return;
        }
        if (request.method()!=GET){
            sendError(ctx,METHOD_NOT_ALLOWED);
            return;
        }
        final String uri=request.uri();
        final String path=sanitizeUri(uri);
        if (path==null){
            sendError(ctx,FORBIDDEN);
            return;
        }
        File file=new File(path);
        if (file.isHidden()||!file.exists()){
            sendError(ctx,NOT_FOUND);
            return;
        }
        if (!file.isFile()){
            sendError(ctx,FORBIDDEN);
            return;
        }

        RandomAccessFile randomAccessFile=null;
        try {
            randomAccessFile=new RandomAccessFile(file,"r");
        }catch (FileNotFoundException e){
            sendError(ctx,NOT_FOUND);
            return;
        }

        long fileLength=randomAccessFile.length();
        DefaultHttpResponse response=new DefaultHttpResponse(HttpVersion.HTTP_1_1,OK);
        HttpHeaderUtil.setContentLength(response,fileLength);


    }

    private String sanitizeUri(String uri) {
        return uri;
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus badRequest) {

    }
}
