package com.amaranth.structlog.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class StructLogAppConfig {
	private static XMLConfiguration config;
	private static boolean enableStructLog;
	
	static {
		try {
			config = new XMLConfiguration("config.xml");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setAppConfig(String fileName) {
		try {
			config = new XMLConfiguration(fileName);
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

	public static String getDatabaseToUse() {
		return config.getString("databaseToUse");
	}

	public static boolean isEnableStructLog() {
		return enableStructLog;
	}

	public static void setEnableStructLog(boolean enableStructLog) {
		StructLogAppConfig.enableStructLog = enableStructLog;
	}
}
