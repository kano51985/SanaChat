package com.sana.netty.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String uri = req.uri();
        // 若要使用http请求来传递数据，请务必记得将http和ws协议分开处理！
        if ("/check".equals(uri)) {
            // 处理 /auth 路径的HTTP请求
            HttpHeaders headers = req.headers();
            String token = headers.get("Authorization");
            if (token != null) {
                System.out.println("Token from client: " + token + " BY HttpRequestHandler");
                // 进行身份验证或其他处理
            }
            // 返回一个简单的HTTP响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
            ctx.writeAndFlush(response);
        } else if ("/ws".equals(uri)) {
            // 如果是WebSocket路径，继续进行WebSocket握手
            ctx.fireChannelRead(req.retain());
        } else {
            // 对于其他路径的处理
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }
}

