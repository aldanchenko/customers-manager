package ua.customermanager.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Customer manager application database configuration.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"ua.customermanager"})
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = {
        "ua.customermanager.common.model",
        "ua.customermanager.backend.repository"
}, entityManagerFactoryRef = "entityManagerFactory")
public class DataBaseConfiguration {

    @Autowired
    private CustomerManagerApplicationProperties applicationProperties;

    /**
     * Inject of LocalContainerEntityManagerFactoryBean.
     *
     * @return a configured LocalContainerEntityManagerFactoryBean instance
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        Properties properties = new Properties();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("ua.customermanager");
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }

    /**
     * DataSource configuration.
     *
     * @return a configured DataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(applicationProperties.getDataBaseClass());
        dataSource.setUrl(applicationProperties.getDatabaseUrl());
        dataSource.setUsername(applicationProperties.getDatabaseUser());
        dataSource.setPassword(applicationProperties.getDatabasePassword());

        return dataSource;
    }

    /**
     * Insect of JpaTransactionManager.
     *
     * @param entityManagerFactory
     *            is an EntityManagerFactory
     * @return a configured JpaTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor inject.
     *
     * @return instance of PersistenceExceptionTranslationPostProcessor
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
