package ua.customermanager.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Customer manager application configuration.
 */
@Configuration
@ComponentScan(basePackages = {
        "ua.customermanager.backend.configuration",
        "ua.customermanager.common.model",
        "ua.customermanager.backend.repository",
        "ua.customermanager.backend.service"
})
public class CustomerManagerConfiguration {

    /**
     * Placeholder config for ${}s.
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
