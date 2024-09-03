package com.sana.netty.handler;

import com.sana.netty.constants.AttributeKeys;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class HttpRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("----------------------------------------触发事件-------------------------------------");

        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            String uri = handshakeComplete.requestUri();
            log.info("uri:{}",uri);
            if (uri != null && uri.contains("/ws") && uri.contains("?")) {
                String[] uriArray = uri.split("\\?");
                if (uriArray != null && uriArray.length > 1) {
                    String[] paramsArray = uriArray[1].split("=");
                    if (paramsArray != null && paramsArray.length > 1) {
                        String uid = paramsArray[1];
                        log.info("ws提取userId成功   By HttpRequestHandler");
                        ctx.attr(AttributeKeys.USER_ID).set(uid);
                    }
                }
            }
            /**
             * 实现自己的初始化操作
             */

        }
        super.userEventTriggered(ctx, evt);
    }
}
