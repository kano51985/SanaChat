package com.sana.netty.handler;

import com.sana.netty.utils.MsgUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 收到消息后进行处理
        String received = msg.text();
        System.out.println("ReceivedMsg: " + received);
        // 响应客户端
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Message received: " + received));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected: " + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client disconnected: " + ctx.channel().id().asLongText());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("----------------------------------------触发事件-------------------------------------");

        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete =
                    (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            String uri = handshakeComplete.requestUri();
            log.info("uri:{}",uri);
            if (uri != null && uri.contains("/ws") && uri.contains("?")) {
                String[] uriArray = uri.split("\\?");
                if (uriArray != null && uriArray.length > 1) {
                    String[] paramsArray = uriArray[1].split("=");
                    if (paramsArray != null && paramsArray.length > 1) {
                        String uid = paramsArray[1];
                        log.info("ws提取userId成功:" + uid + "  By HttpRequestHandler");
                        MsgUtils.setUserId(ctx.channel(),uid);
                    }
                }
            }
        }
        super.userEventTriggered(ctx, evt);

    }
}
