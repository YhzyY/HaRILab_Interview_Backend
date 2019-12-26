package com.lab.backend;

//import com.zaxxer.hikari.*;
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.*;
//import javax.sql.DataSource;
//import java.net.URI;
//import java.net.URISyntaxException;


//@Configuration
//public class DatabaseConfig {
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dbUrl);
//        return new HikariDataSource(config);
//    }
//}


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.lab.backend")
public class DatabaseConfig {
    @Resource
    private Environment env;

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder, JpaProperties jpaProperties,
//            HibernateProperties hibernateProperties) {
//        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
//                jpaProperties.getProperties(), new HibernateSettings());
//        return builder.dataSource(dynamicDataSource()).properties(properties)
//                .packages("com.openstring.resource.common.entity").build();
//    }


    @Bean
    public BasicDataSource dataSource() throws URISyntaxException, SQLException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        URI dbUri = new URI("postgres://fywqvaxrntqfjy:4a692af342390f64c76c980f62fd097634dcf4dcd2684e547d5c27a39729e1b6@ec2-174-129-24-148.compute-1.amazonaws.com:5432/d3hh9aiaah1ili");


        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

}