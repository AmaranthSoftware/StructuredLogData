package com.amaranth.structlog.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class AppConfig {
	private static XMLConfiguration config;
	static {
		try {
			config = new XMLConfiguration("config.xml");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getMongoDBUrl() {
		return config.getString("databases.mongodb.url");
	}

	public static String getMongoDBName() {
		return config.getString("databases.mongodb.name");
	}
}
