package com.amaranth.structlog;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.struct.StructLog;
import com.amaranth.structlog.struct.StructLogFactory;

/**
 * Unit test TestStructLog.
 */
public class TestEmptyStructLog {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	final String componentName = "component1";

	@Test
	public void testStoringExceptions1() {
		StructLogAppConfig.setAppConfig("test/emptydb-config.xml");	
		Assert.assertEquals(StructLogAppConfig.getDatabaseToUse(), "empty");		
		String id = null;
		final StructLog slog = StructLogFactory.getRootStructLog(componentName);
		id = slog.getId();		
		slog.close(); 
	}

}
