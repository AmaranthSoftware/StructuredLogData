package com.amaranth.structlog.db.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.amaranth.structlog.config.StructLogAppConfig;
import com.amaranth.structlog.struct.StructLog;

public class StructLogDao extends BasicDAO<StructLog, String> {

	private StructLogDao(Datastore ds) {
		super(MongoDB.getInstance().getDatastore());
	}

	public static StructLogDao getInstance() {
		if (StructLogAppConfig.isEnableStructLog()) {
			return new StructLogDao(MongoDB.getInstance().getDatastore());
		}
		return null;
	}

	/**
	 * Find all records with matching 'name'. This list will be reverse-sorted
	 * by startTimestamp(latest first, and oldest last). Total number of records
	 * will be less than or equal to 'limit'
	 * 
	 * @param name
	 * @param limit
	 *            Limits number of record
	 *            <ul>
	 *            <li>for value <0 behavior is undefined. Asserted with
	 *            IllegalArgumentException.
	 *            <li>0 means no limit
	 *            <li>>0 means restriction on upto 'limit' number of records.
	 *            </ul>
	 * @return
	 */
	public List<StructLog> findByName(String name, long limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit value should be >= 0.");
		}
		List<StructLog> list = new ArrayList<>();

		if (StringUtils.isEmpty(name)) {
			return list;
		}

		Datastore ds = MongoDB.getInstance().getDatastore();
		Query<StructLog> q = ds.createQuery(StructLog.class).field("name")
				.equal(name).order("-startTimestamp");
		if (limit >= 0) {
			q = q.limit((int) limit);
		}

		return q.asList();
	}

	/**
	 * Return records which have matching 'name' and startTimestamp greater than
	 * or equal to input startTimestamp.
	 * <p>
	 * FIXME: Write test cases for findByNameAndStartTimestamp
	 */
	public List<StructLog> findByNameAndStartTimestamp(String name,
			int startTimestamp) {
		List<StructLog> list = new ArrayList<>();

		if (StringUtils.isEmpty(name)) {
			return list;
		}
		Datastore ds = MongoDB.getInstance().getDatastore();
		Query<StructLog> q = ds.createQuery(StructLog.class).field("name")
				.equal(name).field("startTimestamp")
				.greaterThanOrEq(startTimestamp);
		return q.asList();
	}
}
