package com.amaranth.structlog.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.amaranth.structlog.config.AppConfig;
import com.mongodb.MongoClient;

public class MongoDB {
	private final static String SERVER_URL = AppConfig.getMongoDBUrl();
	private final static String DB_NAME = AppConfig.getMongoDBName();

	private static MongoDB instance = new MongoDB();

	public static MongoDB getInstance() {
		return MongoDB.instance;
	}

	private final MongoClient mongoClient = new MongoClient(MongoDB.SERVER_URL);
	private final Morphia morphia = new Morphia();
	private final Datastore ds = morphia.createDatastore(mongoClient,
			MongoDB.DB_NAME);

	public Datastore getDatastore() {
		return ds;
	}

	private MongoDB() {
	}
}
