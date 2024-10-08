package com.sana.netty;

import com.sana.netty.handler.HttpAuthHandler;
import com.sana.netty.handler.HttpRequestHandler;
import com.sana.netty.handler.WebSocketFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import jakarta.annotation.PreDestroy;

import org.springframework.stereotype.Component;


@Component

public class NettyWebSocketServer implements Runnable{

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @PreDestroy
    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @Override
    public void run() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 处理 http 跨域
                            CorsConfig corsConfig = CorsConfig.withAnyOrigin()
                                    .allowedRequestHeaders("Authorization","Content-Type")
                                    .allowedRequestMethods(HttpMethod.GET, HttpMethod.POST)
                                    .build();


                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 跨域处理
                            pipeline.addLast(new CorsHandler(corsConfig));
                            // 认证处理器与请求参数提取处理器
//                            pipeline.addLast(new HttpAuthHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true,65536 * 10,false,true));
//                            pipeline.addLast(new HttpRequestHandler());
                            // 自己的handler
                            pipeline.addLast(new WebSocketFrameHandler());
                        }
                    });

            ChannelFuture future = serverBootstrap.bind(8081).sync();
            System.out.println("Netty WebSocket server started on port 8081");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

