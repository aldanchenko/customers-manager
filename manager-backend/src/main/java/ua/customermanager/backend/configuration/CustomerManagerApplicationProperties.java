package ua.customermanager.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Customer manager application properties.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class CustomerManagerApplicationProperties {

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.class}")
    private String dataBaseClass;

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDataBaseClass() {
        return dataBaseClass;
    }

    public void setDataBaseClass(String dataBaseClass) {
        this.dataBaseClass = dataBaseClass;
    }
}
