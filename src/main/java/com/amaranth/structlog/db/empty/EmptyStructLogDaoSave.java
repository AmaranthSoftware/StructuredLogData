package com.amaranth.structlog.db.empty;

import com.amaranth.structlog.db.IDaoSave;
import com.amaranth.structlog.struct.StructLog;

public class EmptyStructLogDaoSave implements IDaoSave {

	private final static EmptyStructLogDaoSave emptyStructLogDaoSave = new EmptyStructLogDaoSave();

	public static EmptyStructLogDaoSave getInstance() {
		return emptyStructLogDaoSave;
	}

	@Override
	public void save(StructLog structLog) {
		// Do nothing.

		// FIXME
		// Do logging.
	}
}
