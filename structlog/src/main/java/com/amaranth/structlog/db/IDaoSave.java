package com.amaranth.structlog.db;

import com.amaranth.structlog.struct.StructLog;

public interface IDaoSave {
	void save(final StructLog structLog);
}
