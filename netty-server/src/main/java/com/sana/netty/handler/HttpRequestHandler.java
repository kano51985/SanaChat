package com.sana.netty.handler;

import com.sana.netty.constants.AttributeKeys;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.AttributeKey;
import io.netty.handler.codec.http.QueryStringDecoder;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String uri = req.uri();

        // 解析WebSocket连接请求的URI
        if (uri.startsWith("/ws")) {
            // 使用QueryStringDecoder来解析URI
            QueryStringDecoder decoder = new QueryStringDecoder(uri);
            // 从查询参数中获取userId
            String userId = decoder.parameters().get("userId").get(0);

            if (userId != null) {
                System.out.println("Extracted userId from URI: " + userId);
                // 存储userId到Channel的Attribute中
                ctx.channel().attr(AttributeKeys.USER_ID).set(userId);
            } else {
                System.err.println("Error: userId is null or missing in URI");
            }

            // 继续进行WebSocket握手
            ctx.fireChannelRead(req.retain());
        } else {
            // 处理其他HTTP请求
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            String userId = ctx.channel().attr(AttributeKeys.USER_ID).get();
            if (userId != null) {
                System.out.println("Handshake complete. UserId: " + userId);
            } else {
                System.err.println("Handshake complete but userId is null.");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
