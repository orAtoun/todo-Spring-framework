package com.todos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean( name = "datasource" )
    @ConfigurationProperties( prefix = "spring.datasource" )
    @Primary
    public DataSource createDataSource() {
        return DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver").build();
    }

    @Bean( name = "jdbcService" )
    @Autowired
    public JdbcTemplate createJdbcTemplate( @Qualifier( "datasource" ) DataSource serviceDS ) {
        return new JdbcTemplate( serviceDS );
    }
}