package com.upgrad.user.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import com.upgrad.user.repository.UserRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(dynamoDBMapperConfigRef = "dynamoDBMapperConfig", basePackageClasses = UserRepository.class)
public class DynamoDBConfig {

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverride) {
        return new DynamoDBMapperConfig.Builder()
                .withTypeConverterFactory(DynamoDBTypeConverterFactory.standard())
                .withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE)
                .withTableNameOverride(tableNameOverride).build();
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig.TableNameOverride tableNameOverride() {
        String userTableName = System.getenv("USERS_TABLE_NAME");
        return DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(userTableName);
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
