package com.amaranth.structlog.mongo.entity;

import com.amaranth.structlog.mongo.dao.StructLogDao;
import com.amaranth.structlog.struct.StructLog;

public class StructLogEntity extends StructLog {

	public StructLogEntity(String componentName) {
		super(componentName);
	}

	@Override
	protected void save() {
		StructLogDao.getInstance().save(this);
	}

}
