package org.peartopeer.accounting.middleware.server.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaServiceConfiguration {

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Bean
	LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("org.peartopeer.accounting");
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
		hibernateProperties.setProperty(AvailableSettings.SHOW_SQL, "true");
		// hibernateProperties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		hibernateProperties.setProperty(AvailableSettings.STORAGE_ENGINE, "innodb");
		hibernateProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		hibernateProperties.setProperty("hibernate.jdbc.batch_size", "10");
		hibernateProperties.setProperty("hibernate.order_updates", "true");
		hibernateProperties.setProperty("hibernate.batch_versioned_data", "true");
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		return sessionFactoryBean;
	}

}