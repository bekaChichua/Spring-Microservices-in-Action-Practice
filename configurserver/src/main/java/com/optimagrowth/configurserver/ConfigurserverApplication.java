package com.optimagrowth.configurserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurserverApplication.class, args);
    }

}
