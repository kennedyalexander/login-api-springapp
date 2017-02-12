package com.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan({ "com" })

public class WebConfig {

	@Bean(name = "h2Db", destroyMethod = "shutdown")
	public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	    	       .generateUniqueName(true)
	               .setType(EmbeddedDatabaseType.H2)
	               .setScriptEncoding("UTF-8")
	               .ignoreFailedDrops(true)
	               .addScript("db-schema.sql")
	               .addScript("db-initalData.sql")
	               .build();
	}

    @Bean
    public JdbcTemplate h2JdbcTemplate(@Qualifier("h2Db") DataSource sqliteDb) { 
        return new JdbcTemplate(sqliteDb); 
    } 


}

