package com.example.awssesdemo;

import com.amazonaws.services.s3.model.ObjectMetadata;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreemarkerConfig {
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
        TemplateLoader loader = new ClassTemplateLoader(this.getClass(), "/templates");
        configuration.setTemplateLoader(loader);
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setConfiguration(configuration);
        return configurer;
    }

    @Bean
    ObjectMetadata objectMetadata(){
        return new ObjectMetadata();
    }
}
