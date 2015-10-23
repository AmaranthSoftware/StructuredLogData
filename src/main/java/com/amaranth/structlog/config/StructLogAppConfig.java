package com.amaranth.structlog.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class StructLogAppConfig {
	private static XMLConfiguration appConfig;

	/**
	 * XML formatted fileName is parsed to initialize XMLCOnfiguration object appConfig.
	 * If for any reason parsing fails, then appConfig will be null and that will be interpreted as StuctLogAppConfig is disabled.
	 * To Enable the StructLogAppConfig, call setAppConfig with correct file name.
	 * @param fileName
	 */
	public static void setAppConfig(String fileName) {
		try {
			appConfig = new XMLConfiguration(fileName);
		} catch (ConfigurationException e) {
			appConfig = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getMongoDBUrl() {
		return getAppConfig().getString("databases.mongodb.url");
	}

	public static String getMongoDBName() {
		return getAppConfig().getString("databases.mongodb.name");
	}

	public static String getDatabaseToUse() {
		return getAppConfig().getString("databaseToUse");
	}

	public static boolean isStructLogConfigInitialized() {
		if (null == appConfig) {
			return false;
		}
		return true;
	}

	public static XMLConfiguration getAppConfig() {
		return appConfig;
	}

	public static void setAppConfig(XMLConfiguration appConfig) {
		StructLogAppConfig.appConfig = appConfig;
	}
}
