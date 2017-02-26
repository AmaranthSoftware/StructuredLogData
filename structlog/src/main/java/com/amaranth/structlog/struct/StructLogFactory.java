package com.amaranth.structlog.struct;

import com.amaranth.structlog.db.IDaoSave;
import com.amaranth.structlog.struct.util.SerDeserHelper;

public class StructLogFactory {

	private static StructLog getInstance(final String componentName,
			final boolean isRoot, final IDaoSave daoSave) {
			return StructLog.getInstance(componentName, isRoot, daoSave);
	}

	public static StructLog getRootStructLog(String componentName, final IDaoSave daoSave) {
		return getInstance(componentName, true, daoSave);
	}

	public static StructLog getStructLog(String componentName, final IDaoSave daoSave) {
		return getInstance(componentName, false, daoSave);
	} 
	
	public static StructLog getStructLogFromJson(String jsonString) 
	{
		return SerDeserHelper.getObjectFromJsonString(jsonString, StructLog.class);
	}

}
