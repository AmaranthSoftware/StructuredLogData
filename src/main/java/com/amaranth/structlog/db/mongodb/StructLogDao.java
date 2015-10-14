package com.amaranth.structlog.db.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.struct.StructLog;

public class StructLogDao extends BasicDAO<StructLog, String> {

	private StructLogDao(Datastore ds) {
		super(MongoDB.getInstance().getDatastore());
	}

	public static StructLogDao getInstance() {
		if (StructLogAppConfig.isEnableStructLog()) {
			return new StructLogDao(MongoDB.getInstance().getDatastore());
		}
		return null;
	}
}
