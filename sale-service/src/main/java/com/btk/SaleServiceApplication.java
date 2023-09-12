package com.btk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.File;

@SpringBootApplication
@EnableFeignClients
public class SaleServiceApplication {
    public static void main(String[] args) {
        String logFilePath = "sale-service/src/main/resources/logs/sale-app.log";

        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            logFile.delete();
        }
        SpringApplication.run(SaleServiceApplication.class);
    }
}