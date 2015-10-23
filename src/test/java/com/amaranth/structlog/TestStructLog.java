package com.amaranth.structlog;

import org.apache.commons.lang.exception.ExceptionUtils;
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
	private static final String CONFIG_XML_FILE = "test/config.xml";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	final String componentName = "component1";

	/**
	 * Closing the StructLog automatically (as expected by default.)
	 */
	// TODO: Integrate embedded MongoDB for testing.
	@Test
	public void testStructLogEntitySave1() {
		StructLogAppConfig.setAppConfig(CONFIG_XML_FILE);
		
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
		StructLogAppConfig.setAppConfig(CONFIG_XML_FILE);
		
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
	@Test
	public void testStructLogEntityDisabled() { 
		StructLogAppConfig.setAppConfig("");
		Assert.assertEquals(StructLogAppConfig.isStructLogConfigInitialized(), false);
		StructLogAppConfig.setAppConfig(CONFIG_XML_FILE);
		Assert.assertEquals(StructLogAppConfig.isStructLogConfigInitialized(), true);
		StructLogAppConfig.setAppConfig("");
		Assert.assertEquals(StructLogAppConfig.isStructLogConfigInitialized(), false);
	}
	@Test
	public void testStoringExceptions1() {
		StructLogAppConfig.setAppConfig(CONFIG_XML_FILE);
		 
		String id = null;
		final StructLog slog = StructLogFactory.getRootStructLog(componentName);
		id = slog.getId();
		
		Exception e=null;
		try {
			e.printStackTrace();
		}
		catch (Exception t) {
			System.out.println(ExceptionUtils.getMessage(t) + ExceptionUtils.getFullStackTrace(t) +  ExceptionUtils.getRootCauseMessage(t) +  ExceptionUtils.getRootCauseStackTrace(t)); 
			slog.addExceptionCaught(t);
		}		
		slog.close(); 
		final StructLog result = StructLogDao.getInstance().findOne("_id", id);
		StructLogDao.getInstance().delete(result);
	}

}
