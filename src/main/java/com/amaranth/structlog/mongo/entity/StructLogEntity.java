package com.amaranth.structlog.mongo.entity;

import com.amaranth.structlog.mongo.dao.StructLogDao;
import com.amaranth.structlog.struct.StructLog;

public class StructLogEntity extends StructLog {

	// Who knew serialization would fail without default CTOR. :|
	public StructLogEntity() {
		super("defaultComponentName");
	}
	
	public StructLogEntity(String componentName) {
		super(componentName);
	}

	@Override
	protected void save() {
		StructLogDao.getInstance().save(this);
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
}
