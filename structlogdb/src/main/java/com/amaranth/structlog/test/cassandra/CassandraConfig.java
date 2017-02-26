package com.amaranth.structlog.test.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:/com/amaranth/structlog/properties/cassandra.properties" })
//classpath:/com/${my.placeholder:default/path}/app.properties
@EnableCassandraRepositories(basePackages = { "com.amaranth.structlog" })
public class CassandraConfig {

  private static final Logger LOG = LoggerFactory.getLogger(CassandraConfig.class);

  @Autowired
  private Environment env;

  @Bean
  public CassandraClusterFactoryBean cluster() {

    CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
    cluster.setContactPoints(env.getProperty("cassandra.contactpoints"));
    cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));

    return cluster;
  }

  @Bean
  public CassandraMappingContext mappingContext() {
    return new BasicCassandraMappingContext();
  }

  @Bean
  public CassandraConverter converter() {
    return new MappingCassandraConverter(mappingContext());
  }

  @Bean
  public CassandraSessionFactoryBean session() throws Exception {

    CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
    session.setCluster(cluster().getObject());
    session.setKeyspaceName(env.getProperty("cassandra.keyspace"));
    session.setConverter(converter());
    session.setSchemaAction(SchemaAction.NONE);

    return session;
  }

  @Bean
  public CassandraOperations cassandraTemplate() throws Exception {
    return new CassandraTemplate(session().getObject());
  }
}