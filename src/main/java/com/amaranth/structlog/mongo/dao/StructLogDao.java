package com.amaranth.structlog.mongo.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.amaranth.structlog.mongo.db.MongoDB;
import com.amaranth.structlog.struct.StructLog;

public class StructLogDao extends BasicDAO<StructLog, String> {

	protected StructLogDao(Datastore ds) {
		super(ds);
	}

	public static StructLogDao getInstance() {
		return new StructLogDao(MongoDB.getInstance().getDatastore());
	}
}
