package ru.goryacms.testmvc;

import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "ru.goryacms.testmvc",
        entityManagerFactoryRef = "testmvcEntityManager",
        transactionManagerRef = "testmvcTransactionManager")
public class Application {
    private static final String HIBERNATE_DDL = "hibernate.ddl-auto";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
    private static final String HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "testmvcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "testmvcEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("testmvcDataSource") DataSource dataSource,
            @Qualifier("testmvcJpaProperties") Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(false);

        emFactory.setJpaVendorAdapter(vendorAdapter);
        emFactory.setJpaProperties(jpaProperties);
        emFactory.setPackagesToScan("ru.goryacms.testmvc");
        return emFactory;
    }

    @Bean(name = "testmvcJpaProperties")
    public Properties getJpaProperties(Environment env) {
        Properties jpaProperties = new Properties();
        jpaProperties.putIfAbsent(HIBERNATE_DDL, env.getProperty(HIBERNATE_DDL));
        jpaProperties.putIfAbsent(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        jpaProperties.putIfAbsent(HIBERNATE_FORMAT_SQL, env.getProperty(HIBERNATE_FORMAT_SQL));
        jpaProperties.putIfAbsent(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
        jpaProperties.putIfAbsent(HIBERNATE_USE_SQL_COMMENTS, env.getProperty(HIBERNATE_USE_SQL_COMMENTS));
        jpaProperties.putIfAbsent(HIBERNATE_JDBC_FETCH_SIZE, env.getProperty(HIBERNATE_JDBC_FETCH_SIZE));
        jpaProperties.putIfAbsent(HIBERNATE_JDBC_BATCH_SIZE, env.getProperty(HIBERNATE_JDBC_BATCH_SIZE));
        return jpaProperties;
    }

    @Bean(name = "testmvcTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("testmvcEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
