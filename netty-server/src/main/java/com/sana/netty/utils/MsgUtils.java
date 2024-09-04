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

    private static final Map<Channel, String> userIdMap = new ConcurrentHashMap<>();

    public static void setUserId(Channel channel, String userId) {
        userIdMap.put(channel, userId);
    }

    public static String getUserId(Channel channel) {
        return userIdMap.get(channel);
    }

    public static void removeUserId(Channel channel) {
        userIdMap.remove(channel);
    }
}


