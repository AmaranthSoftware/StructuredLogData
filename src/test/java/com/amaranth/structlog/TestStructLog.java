package com.amaranth.structlog;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.db.mongodb.StructLogDao;
import com.amaranth.structlog.struct.StructLog;
import com.amaranth.structlog.struct.StructLogFactory;

/**
 * Unit test TestStructLog.
 */
public class TestStructLog {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	final String componentName = "component1";

	/**
	 * Closing the StructLog automatically (as expected by default.)
	 */
	// TODO: Integrate embedded MongoDB for testing.
	@Test
	public void testStructLogEntitySave1() {
		StructLogAppConfig.setEnableStructLog(true);
		String id = null;
		try (StructLog slog = StructLogFactory.getRootStructLog(componentName)) {
			id = slog.getId();
		}

		final StructLog result = StructLogDao.getInstance().findOne("_id", id);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), id);
		Assert.assertEquals(result.getName(), componentName);
		StructLogDao.getInstance().delete(result);
	}

	/**
	 * With explicit "close()"
	 */
	// TODO: Integrate embedded MongoDB for testing.
	@Test
	public void testStructLogEntitySave2() {
		StructLogAppConfig.setEnableStructLog(true);
		String id = null;
		final StructLog slog = StructLogFactory.getRootStructLog(componentName);
		id = slog.getId();
		slog.close();
		final StructLog result = StructLogDao.getInstance().findOne("_id", id);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), id);
		Assert.assertEquals(result.getName(), componentName);
		StructLogDao.getInstance().delete(result);
	}

}
