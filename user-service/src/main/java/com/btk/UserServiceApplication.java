package com.btk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.File;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {
    public static void main(String[] args) {
        String logFilePath = "user-service/src/main/resources/logs/user-app.log";

        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            logFile.delete();
        }
        SpringApplication.run(UserServiceApplication.class);
    }
}