package com.amaranth.structlog.mongo.db;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoDB {
	//TODO: Read following values from AppConfig
//	private final static String SERVER_URL = "127.0.0.1:27017";
	private final static String SERVER_URL = "10.64.220.117:27017";
	private final static String DB_NAME = "amaranthtestdb";

	private static MongoDB instance = new MongoDB();
	public static MongoDB getInstance()
	{
		return MongoDB.instance;
	}

	private final MongoClient mongoClient = new MongoClient(MongoDB.SERVER_URL);
	private final Morphia morphia = new Morphia();
	private final Datastore ds = morphia.createDatastore(mongoClient, MongoDB.DB_NAME);

	public Datastore getDatastore()
	{
		return ds;
	}

	private MongoDB() {
	}
}
