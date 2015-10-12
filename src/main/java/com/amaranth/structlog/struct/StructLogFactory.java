package com.amaranth.structlog.struct;

import com.amaranth.structlog.config.AppConfig;
import com.amaranth.structlog.mongodb.StructLogDaoSave;

public class StructLogFactory {

	private static StructLog getInstance(final String componentName,
			final boolean isRoot) {
		if (AppConfig.getDatabaseToUse().equals("mongodb")) {
			return StructLog.getInstance(componentName, isRoot,
					StructLogDaoSave.getInstance());
		}
		return null;
	}

	public static StructLog getRootStructLog(String componentName) {
		return getInstance(componentName, true);
	}

	public static StructLog getStructLog(String componentName) {
		return getInstance(componentName, false);
	}

}
