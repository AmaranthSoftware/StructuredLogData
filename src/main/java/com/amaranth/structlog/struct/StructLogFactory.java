package com.amaranth.structlog.struct;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.struct.util.SerDeserHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StructLogFactory {

	private static StructLog getInstance(final String componentName,
			final boolean isRoot) {
		if (StructLogAppConfig.getDatabaseToUse().equals("mongodb")) {
			return StructLog.getInstance(componentName, isRoot);
		}
		return null;
	}

	public static StructLog getRootStructLog(String componentName) {
		return getInstance(componentName, true);
	}

	public static StructLog getStructLog(String componentName) {
		return getInstance(componentName, false);
	} 
	
	public static StructLog getStructLogFromJson(String jsonString) 
	{
		return SerDeserHelper.getObjectFromJsonString(jsonString, StructLog.class);
	}

}
