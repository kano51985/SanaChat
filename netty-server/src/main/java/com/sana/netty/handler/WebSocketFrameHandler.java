package com.sana.netty.handler;

import com.sana.netty.constants.AttributeKeys;
import com.sana.netty.utils.MsgUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.util.StringUtils;


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
        String userId = ctx.attr(AttributeKeys.USER_ID).get();
//        if (StringUtils.hasLength(userId)) {
            System.out.println("channel=======================>"+MsgUtils.getChannel("1"));
//        }
        System.out.println("Client connected: " + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client disconnected: " + ctx.channel().id().asLongText());
    }


}
