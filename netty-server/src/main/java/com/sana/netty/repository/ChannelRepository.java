package com.sana.netty.repository;

import io.netty.channel.Channel;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class ChannelRepository {
    private static final Map<String, Channel> channels = new ConcurrentHashMap<>();

    public static void addChannel(String userId, Channel channel) {
        if (userId != null && channel != null) {
            channels.put(userId, channel);
        }
    }

    public static void removeChannel(String userId) {
        if (userId != null) {
            channels.remove(userId);
        }
    }

    public static Channel getChannel(String userId) {
        return channels.get(userId);
    }
}


