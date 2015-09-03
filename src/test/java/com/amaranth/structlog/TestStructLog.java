package com.amaranth.structlog;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.amaranth.structlog.mongo.dao.StructLogDao;
import com.amaranth.structlog.mongo.entity.StructLogEntity;
import com.amaranth.structlog.struct.StructLog;

/**
 * Unit test TestStructLog.
 */
public class TestStructLog
{
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	final String componentName = "component1";

	/**
	 * Closing the StructLog automatically (as expected by default.)
	 */
//TODO: Integrate embedded MongoDB for testing.	
//	@Test
	public void testStructLogEntitySave1() {
		String id = null;
		try (StructLog slog = new StructLogEntity(componentName))
		{
			id = slog.getId();
		}

		final StructLog result = StructLogDao.getInstance().findOne("_id", id);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), id);
		Assert.assertEquals(result.getComponentName(), componentName);
		StructLogDao.getInstance().delete((StructLogEntity) result);
	}

	/**
	 * With explicit "close()"
	 */
//TODO: Integrate embedded MongoDB for testing.	
//	@Test
	public void testStructLogEntitySave2() {
		String id = null;
		final StructLog slog = new StructLogEntity(componentName);
		id = slog.getId();
		slog.close();
		final StructLog result = StructLogDao.getInstance().findOne("_id", id);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), id);
		Assert.assertEquals(result.getComponentName(), componentName);
		StructLogDao.getInstance().delete((StructLogEntity) result);
	}
}
