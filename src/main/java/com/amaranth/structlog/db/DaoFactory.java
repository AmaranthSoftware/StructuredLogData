package com.amaranth.structlog.db;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.mongodb.StructLogDaoSave;

public class DaoFactory {
	public static StructLogDaoSave getInstance() {
		if (StructLogAppConfig.getDatabaseToUse().equals("mongodb")) {
			return StructLogDaoSave.getInstance();
		}
		return null;
	}
}
