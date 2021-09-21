package com.renan.todolistapi.adapters.repositories.config;

import java.util.Arrays;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories
  (basePackages = "com.renan.todolistapi.adapters.repositories")
public class DynamoDBConfig implements InitializingBean {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        
        if (StringUtils.hasText(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
        
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
          amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        
        try {
            amazonDynamoDB().createTable(
                Arrays.asList(
                    new AttributeDefinition("pk_user_id", ScalarAttributeType.S), 
                    new AttributeDefinition("sk_task_id", ScalarAttributeType.N)
                ),
                "tasks-table",
                Arrays.asList(
                    new KeySchemaElement("pk_user_id", KeyType.HASH), 
                    new KeySchemaElement("sk_task_id", KeyType.RANGE)
                ),
                new ProvisionedThroughput(10L, 10L)
            ).wait();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}