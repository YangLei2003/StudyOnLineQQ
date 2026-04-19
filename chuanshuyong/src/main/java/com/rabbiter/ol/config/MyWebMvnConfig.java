package com.rabbiter.ol.config;

import com.rabbiter.ol.tool.PathUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvnConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/videoFile/**").addResourceLocations("file:" + PathUtils.getClassLoadRootPath() + "/file/videoFile/");
        registry.addResourceHandler("/file/imageFile/**").addResourceLocations("file:" + PathUtils.getClassLoadRootPath() + "/file/imageFile/");
    }
}
