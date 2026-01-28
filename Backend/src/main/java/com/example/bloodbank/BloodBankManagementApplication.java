package com.example.bloodbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.blood",
        "com.example.bloodbank"
})
public class BloodBankManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(BloodBankManagementApplication.class, args);
    }
}
