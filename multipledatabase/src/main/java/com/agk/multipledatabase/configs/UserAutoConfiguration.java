package com.agk.multipledatabase.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db-boot.properties")
@EnableJpaRepositories(
        basePackages = {"com.agk.multipledatabase.User"},
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
public class UserAutoConfiguration extends BaseAutoConfiguration {

    @Override
    String[] getPackagesToScan() {
        return new String[]{"com.agk.multipledatabase.User"};
    }

    @Override
    DataSource getDataSource() {
        return userDataSource();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db1.datasource")
    DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean userEntityManager(){
        return entityManager();
    }

    @Primary
    @Bean
    PlatformTransactionManager userTransactionManager(){
        return transactionManager();
    }
}