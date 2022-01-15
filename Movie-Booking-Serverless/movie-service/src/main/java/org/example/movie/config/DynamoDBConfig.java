package org.example.movie.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import org.example.movie.repository.MovieRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(dynamoDBMapperConfigRef = "dynamoDBMapperConfig", basePackageClasses = MovieRepository.class)
public class DynamoDBConfig {

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverride) {
        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.withTypeConverterFactory(DynamoDBTypeConverterFactory.standard())
                .withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE)
                .withTableNameOverride(tableNameOverride);
        return builder.build();
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig.TableNameOverride tableNameOverride() {
        String tableNameFromEnv = System.getenv("MOVIES_TABLE_NAME");
        return DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableNameFromEnv);
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
    }

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
