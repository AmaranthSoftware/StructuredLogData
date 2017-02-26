package com.amaranth.structlog.db.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.db.IDaoSave;
import com.amaranth.structlog.struct.StructLog;

public class StructLogDaoSave implements IDaoSave {

	private static class StructLogDaoImpl extends BasicDAO<StructLog, String> {
		protected StructLogDaoImpl() {
			super(MongoDB.getInstance().getDatastore());
		}
	}

	private final BasicDAO<StructLog, String> dao;

	private StructLogDaoSave(Datastore ds) {
		dao = new StructLogDaoImpl();
	}

	public static StructLogDaoSave getInstance() {
		return new StructLogDaoSave(MongoDB.getInstance().getDatastore());
	}

	@Override
	public void save(StructLog structLog) {
		if (!StructLogAppConfig.isStructLogConfigInitialized()) {
			return;
		}
		try {
			dao.save(structLog);
		} catch (Exception e) {
			System.err.println("Failed to save structLog="
					+ structLog.toString());
		}		
	}
}
