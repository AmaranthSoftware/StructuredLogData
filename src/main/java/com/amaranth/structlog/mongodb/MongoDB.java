package com.amaranth.structlog.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.mongodb.MongoClient;

public class MongoDB {
	private final String serverUrl;
	private final String dbName;
	private final MongoClient mongoClient;
	private final Morphia morphia;
	private final Datastore ds;

	private static MongoDB instance;

	public static synchronized MongoDB getInstance() {
		if (null == instance) {
			instance = new MongoDB();
		}
		return MongoDB.instance;
	}

	public Datastore getDatastore() {
		return ds;
	}

	private MongoDB() {
		serverUrl = StructLogAppConfig.getMongoDBUrl();
		dbName = StructLogAppConfig.getMongoDBName();
		mongoClient = new MongoClient(serverUrl);
		morphia = new Morphia();
		ds = morphia.createDatastore(mongoClient, dbName);
	}
}
