package org.peartopeer.accounting.middleware.server.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.peartopeer.accounting.middleware.client.services.MyService;
import org.peartopeer.accounting.middleware.server.services.MyServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.username}")
    private String dbUsername;
    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {

        ComboPooledDataSource db = new ComboPooledDataSource();
        try {
            db.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
        }
        db.setJdbcUrl(dbUrl);
        db.setUser(dbUsername);
        db.setPassword(dbPassword);
        db.setAcquireIncrement(5);
        db.setIdleConnectionTestPeriod(10);
        db.setInitialPoolSize(5);
        db.setMinPoolSize(5);
        db.setMaxPoolSize(75);
        db.setMaxStatements(0);
        db.setCheckoutTimeout(25200);
        db.setPreferredTestQuery("select 1");
        db.setTestConnectionOnCheckout(true);

        return db;
    }

    //utile uniquement pour les tests unitaires
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

}
