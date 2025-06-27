package group2.swd392_onlineingredientsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String homeDir = System.getProperty("user.home");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + homeDir + "/images/");
    }
} 