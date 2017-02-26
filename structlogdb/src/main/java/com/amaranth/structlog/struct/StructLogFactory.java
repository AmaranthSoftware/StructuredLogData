package com.amaranth.structlog.struct;

import com.amaranth.structlog.struct.util.SerDeserHelper;

public class StructLogFactory {

	private static StructLog getInstance(final String componentName,
			final boolean isRoot) {
			return StructLog.getInstance(componentName, isRoot);
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
