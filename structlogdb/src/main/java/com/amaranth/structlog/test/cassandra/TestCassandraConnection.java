package com.amaranth.structlog.test.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select; 
 
@Component
@ComponentScan
public class TestCassandraConnection { 
 
 private static final Logger LOG = LoggerFactory.getLogger(TestCassandraConnection.class); 
 
 private static Cluster cluster; 
 private static Session session; 


@Autowired
private CassandraOperations cassandraOperations;

private void test() 
{
	System.out.println( cassandraOperations.toString());
 cassandraOperations.insert(new Person("personId1", "ajeet", 33));
}

 public static void main(String[] args) { 
	 
	 TestCassandraConnection t = new TestCassandraConnection();
	 t.test();
	 
//	 try {
		cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9042).build();
//	} catch (UnknownHostException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
 
   session = cluster.connect("demo"); 
 
   CassandraOperations cassandraOps = new CassandraTemplate(session); 
 
   cassandraOps.insert(new Person("1234567890", "David", 40)); 
 
   Select s = QueryBuilder.select().from("person"); 
   s.where(QueryBuilder.eq("id", "1234567890")); 
 
   LOG.info(cassandraOps.queryForObject(s, Person.class).getId()); 
 
   cassandraOps.truncate("person"); 
 } 
} 