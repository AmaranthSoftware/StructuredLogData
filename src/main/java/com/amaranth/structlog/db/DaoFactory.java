package com.amaranth.structlog.db;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.db.empty.EmptyStructLogDaoSave;
import com.amaranth.structlog.db.mongodb.StructLogDaoSave;

public class DaoFactory {
	public static IDaoSave getInstance() {
		
		if (!StructLogAppConfig.isEnableStructLog()) {
			return EmptyStructLogDaoSave.getInstance();
		}
		
		if (StructLogAppConfig.getDatabaseToUse().equals("mongodb")) {
			return StructLogDaoSave.getInstance();
		}
		StructLogAppConfig.setEnableStructLog(false);
		return null;
	}
}
