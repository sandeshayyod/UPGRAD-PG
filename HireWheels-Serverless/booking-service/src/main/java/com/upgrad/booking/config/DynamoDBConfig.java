package com.upgrad.booking.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import com.upgrad.booking.repository.BookingRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(dynamoDBMapperConfigRef = "dynamoDBMapperConfig", basePackageClasses = BookingRepository.class)
public class DynamoDBConfig {

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverride) {
        return new DynamoDBMapperConfig.Builder()
                .withTypeConverterFactory(DynamoDBTypeConverterFactory.standard())
                .withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE)
                .withTableNameOverride(tableNameOverride)
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig.TableNameOverride tableNameOverride() {
        String bookingTableName = System.getenv("BOOKING_TABLE_NAME");
        return DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(bookingTableName);
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
    }

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClient.builder()
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
