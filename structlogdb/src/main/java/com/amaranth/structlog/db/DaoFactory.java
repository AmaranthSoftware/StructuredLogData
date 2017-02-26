package com.amaranth.structlog.db;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.db.empty.EmptyStructLogDaoSave;
import com.amaranth.structlog.db.mongodb.StructLogDaoSave;

public class DaoFactory {
	public static IDaoSave getInstance() {
		
		if (!StructLogAppConfig.isStructLogConfigInitialized()) {
			return EmptyStructLogDaoSave.getInstance();
		}
		
		if (StructLogAppConfig.getDatabaseToUse().equals("mongodb")) {
			return StructLogDaoSave.getInstance();
		}
		if (StructLogAppConfig.getDatabaseToUse().equals("empty")) {
			return EmptyStructLogDaoSave.getInstance();
		}
		
		// If no DB is mentioned that disable StructLogAppConfig.
		StructLogAppConfig.setAppConfig("");
		return null;
	}
}
