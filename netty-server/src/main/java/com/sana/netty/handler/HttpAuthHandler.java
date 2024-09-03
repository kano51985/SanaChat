package com.sana.netty.handler;

import com.sana.netty.constants.AttributeKeys;
import io.netty.channel.ChannelHandlerContext;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class HttpAuthHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest){
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            /*
             *再这里编写自己的操作
             */
            // 在本channel上移除这个handler消息处理，即只处理一次，鉴权通过与否
            ctx.pipeline().remove(HttpAuthHandler.class);
        super.channelRead(ctx, msg);
        }
    }
}

