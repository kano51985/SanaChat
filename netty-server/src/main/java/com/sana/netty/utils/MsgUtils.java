package com.sana.netty.utils;



import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author YZD
 * @date 2022年10月19日11:31:58
 */
@Slf4j
public class MsgUtils {

    private static final Map<String, ChannelHandlerContext> channels = new ConcurrentHashMap<>();

    public static void addChannel(String userId, ChannelHandlerContext channel) {
        if (userId != null && channel != null) {
            channels.put(userId, channel);
        }
    }

    public static void removeChannel(String userId) {
        if (userId != null) {
            channels.remove(userId);
        }
    }

    public static ChannelHandlerContext getChannel(String userId) {
        return channels.get(userId);
    }
}


