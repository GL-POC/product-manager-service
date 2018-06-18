package io.global.logic.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@EnableAutoConfiguration
@Data
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoConfig {

    @Value("${database:productsDb}")
    private String database;

    @Value("${host:127.0.0.1}")
    private String host;

    @Value("${port:9999}")
    private String port;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        System.err.println(getHost()+":" +Integer.parseInt(getPort())+ " "+getDatabase());
        return new SimpleMongoDbFactory(new MongoClient(getHost(),Integer.parseInt(getPort())), getDatabase());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
