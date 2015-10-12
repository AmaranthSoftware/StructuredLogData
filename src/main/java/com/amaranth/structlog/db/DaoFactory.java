package com.amaranth.structlog.db;

import com.amaranth.structlog.config.AppConfig;
import com.amaranth.structlog.mongodb.StructLogDaoSave;

public class DaoFactory {
	public static StructLogDaoSave getInstance() {
		if (AppConfig.getDatabaseToUse().equals("mongodb")) {
			return StructLogDaoSave.getInstance();
		}
		return null;
	}
}
