package com.sportshop.sportshop.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dvmuyopwa");
        config.put("api_key", "848992124231357");
        config.put("api_secret", "2v4ZU1_M5w7BY-Nma33ssBDABaw");
        return new Cloudinary(config);
    }
}
