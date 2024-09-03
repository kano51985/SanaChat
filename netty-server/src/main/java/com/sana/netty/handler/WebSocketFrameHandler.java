package com.sana.netty.handler;

import com.sana.netty.constants.AttributeKeys;
import com.sana.netty.repository.ChannelRepository;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // 使用ConcurrentHashMap来存储userId与Channel的映射关系
    private static final Map<String, ChannelHandlerContext> userChannelMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 收到消息后进行处理
        String received = msg.text();
        System.out.println("ReceivedMsg: " + received);
        // 响应客户端
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Message received: " + received));
    }

//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        String userId = ctx.channel().attr(AttributeKeys.USER_ID).get();
//        System.out.println("userId ======================= " + userId);
//        if (userId == null) {
//            System.err.println("Error: userId is null in handlerAdded");
//            return;
//        }
//        ChannelRepository.addChannel(userId, ctx.channel());
//        System.out.println("Client connected =======================================: " + userId);
//    }
@Override
public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    ctx.executor().schedule(() -> {
        String userId = ctx.channel().attr(AttributeKeys.USER_ID).get();
        System.out.println("userId ======================= " + userId);
        if (userId == null) {
            System.err.println("Error: userId is null in handlerAdded");
            return;
        }
        ChannelRepository.addChannel(userId, ctx.channel());
        System.out.println("Client connected =======================================: " + userId);
    }, 100, TimeUnit.MILLISECONDS); // 延迟 100 毫秒
}


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String userId = ctx.channel().attr(AttributeKeys.USER_ID).get();
        ChannelRepository.removeChannel(userId);
        System.out.println("Client disconnected==========================================: " + userId);
    }
}
