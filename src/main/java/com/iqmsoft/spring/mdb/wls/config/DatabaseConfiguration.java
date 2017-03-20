package com.iqmsoft.spring.mdb.wls.config;





import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
public class DatabaseConfiguration {

	@Bean
	public DataSource dataSource() {
	    System.out.println("userDBDatasource :: init");
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
	    dataSource.setUsername("root");
	    dataSource.setPassword("good1234");
	    return dataSource;
	}
	
}
