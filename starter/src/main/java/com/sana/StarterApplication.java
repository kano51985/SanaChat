package com.sana;

import com.sana.netty.NettyWebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class,args);
    }
    @Autowired
    private NettyWebSocketServer nettyWebSocketServer;
    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            new Thread(nettyWebSocketServer).start();
            System.out.println("SanaNetty application started.");
        };
    }
}
