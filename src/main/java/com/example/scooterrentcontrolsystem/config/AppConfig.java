package com.example.scooterrentcontrolsystem.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.modelmapper.ModelMapper;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example")
@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //not an uninitialized persistence collection and not null
        modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object src = context.getSource();
            if (src instanceof PersistentCollection) {
                return ((PersistentCollection) src).wasInitialized();
            }
            return !Objects.isNull(src);
        });

        return modelMapper;
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    @Bean
    public DataSource dataSource() {
        Properties prop = new Properties();
        try {
            prop.load(AppConfig.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            log.error("Не удалось открыть property файл", e);
            throw new RuntimeException(e);
        }

        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(prop.getProperty("spring.datasource.driver-class-name"));
        driver.setUrl(prop.getProperty("spring.datasource.url"));
        driver.setUsername(prop.getProperty("spring.datasource.username"));
        driver.setPassword(prop.getProperty("spring.datasource.password"));
        return driver;
    }
}
