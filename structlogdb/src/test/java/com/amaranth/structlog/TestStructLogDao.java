package com.amaranth.structlog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.db.mongodb.StructLogDao;
import com.amaranth.structlog.struct.StructLog;
import com.amaranth.structlog.struct.StructLogFactory;

public class TestStructLogDao {

	@Before
	public void setup() {
		StructLogAppConfig.setAppConfig("config.xml");
	}

	@After
	public void teardown() {
		StructLogAppConfig.setAppConfig("");
	}

	@Test
	public void testQueryFindByName_fullList() {

		List<StructLog> sList = new ArrayList<>();
		String cn = "cn1";
		int count = 10;

		insertRecords(sList, cn, count);

		// Reverse the list because findByName sorts by reverse chronological
		// order.
		Collections.reverse(sList);

		compareLists(sList, count, cn, null, null);

		removeRecords(sList);
	}

	@Test
	public void testQueryFindByName_partialLists() {

		List<StructLog> sList = new ArrayList<>();
		String cn = "cn1";
		int count = 10;

		insertRecords(sList, cn, count);

		// Reverse the list because findByName sorts by reverse chronological
		// order.
		Collections.reverse(sList);

		int subCount = 1;
		compareLists(sList, subCount, cn, null, null);
		subCount = 5;
		compareLists(sList, subCount, cn, null, null);
		subCount = count;
		compareLists(sList, subCount, cn, null, null);
		subCount = -1;
		Exception e = null;
		try {
			compareLists(sList, subCount, cn, null, null);
		} catch (IllegalArgumentException ie) {
			e = ie;
		}
		Assert.assertNotNull(e);
		Assert.assertEquals(e.getClass(), IllegalArgumentException.class);

		removeRecords(sList);
	}

	private void compareLists(List<StructLog> originalList, int subCount,
			String cn, String user, Integer timestamp) {
		List<StructLog> findByNameQueryResult = StructLogDao.getInstance()
				.findByNameUserAndStartTime(cn, user, timestamp, subCount);
		if (subCount == 0) {
			subCount = originalList.size();
		}
		List<StructLog> subListActual = originalList.subList(0, subCount);
		ListIterator<StructLog> it1;
		ListIterator<StructLog> it2;
		for (it1 = findByNameQueryResult.listIterator(), it2 = subListActual
				.listIterator(); it1.hasNext() && it2.hasNext();) {
			StructLog e1 = it1.next();
			StructLog e2 = it2.next();
			Assert.assertEquals(e1.getId(), e2.getId());
		}
		Assert.assertEquals(it1.hasNext(), it2.hasNext());
	}

	private void removeRecords(List<StructLog> sList) {
		for (StructLog e : sList) {
			StructLogDao.getInstance().delete(e);
		}
	}

	private void insertRecords(List<StructLog> sList, String cn, int count) {
		for (int i = 0; i < count; i++) {
			final StructLog slog = StructLogFactory.getRootStructLog(cn);
			slog.close();
			sList.add(slog);
		}
	}
}
