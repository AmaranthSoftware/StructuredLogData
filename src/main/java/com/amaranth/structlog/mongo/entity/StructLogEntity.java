package com.amaranth.structlog.mongo.entity;

import com.amaranth.structlog.mongo.dao.StructLogDao;
import com.amaranth.structlog.struct.StructLog;

public class StructLogEntity extends StructLog {

	// Who knew serialization would fail without default CTOR. :|
	private StructLogEntity() {
		super("defaultComponentName", false);
	}

	private StructLogEntity(String componentName, final boolean isRoot) {
		super(componentName, isRoot);
	}

	public static StructLogEntity getRootStructLog(String componentName) {
		return new StructLogEntity(componentName, true);
	}

	public static StructLogEntity getStructLog(String componentName) {
		return new StructLogEntity(componentName, false);
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
