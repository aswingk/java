package com.agk.multipledatabase.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
@Configuration
@PropertySource("classpath:db-boot.properties")
@EnableJpaRepositories(
        basePackages = "com.agk.multipledatabase.Product",
        transactionManagerRef = "productTransactionManager",
        entityManagerFactoryRef = "productEntityManager"
)
public class ProductAutoConfiguration extends BaseAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    DataSource productDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Override
    DataSource getDataSource() {
        return productDataSource();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean productEntityManager(){
        return entityManager();
    }

    @Bean
    PlatformTransactionManager productTransactionManager(){
        return transactionManager();
    }

    @Override
    String[] getPackagesToScan() {
        return new String[]{"com.agk.multipledatabase.Product"};
    }
}