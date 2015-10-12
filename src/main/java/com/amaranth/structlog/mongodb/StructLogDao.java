package com.amaranth.structlog.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.amaranth.structlog.struct.StructLog;

public class StructLogDao extends BasicDAO<StructLog, String> {

	private StructLogDao(Datastore ds) {
		super(MongoDB.getInstance().getDatastore());
	}

	public static StructLogDao getInstance() {
		return new StructLogDao(MongoDB.getInstance().getDatastore());
	}
}
