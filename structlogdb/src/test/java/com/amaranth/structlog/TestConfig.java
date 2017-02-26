package com.amaranth.structlog;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@ComponentScan("com.amaranth.structlog")
@Configuration
//@PropertySource(value = { "classpath:cassandra.properties" })
@PropertySource(value = { "file:/Users/ajeetganga/git/amaranthsoftware/StructuredLogData/src/main/java/com/amaranth/structlog/properties/cassandra.properties" })
public class TestConfig {
	@Autowired
	  private Environment env;
	
	@Test
	public void test1()
	{
		Assert.assertNotNull("env variable should be non-null.", env);
		System.out.println(Integer.parseInt(env.getProperty("cassandra.port")));
	}

}
