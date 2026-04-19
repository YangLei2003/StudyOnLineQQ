package com.rabbiter.ol;

import com.rabbiter.ol.tool.PathUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineLearnApplication {

    public static void main(String[] args) {
        System.out.println("Project Path : " + PathUtils.getClassLoadRootPath());
        SpringApplication.run(OnlineLearnApplication.class, args);
    }

}
