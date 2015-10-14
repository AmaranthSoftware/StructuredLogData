package com.amaranth.structlog.struct;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.amaranth.structlog.db.DaoFactory;
import com.amaranth.structlog.db.IDaoSave;

public class StructLog extends StructLogPojo implements AutoCloseable {

	private static final String IS_ROOT = "IS_ROOT";
	private static final String COMPONENT_NAME = "COMPONENT_NAME";

	private StructLog() {
		getAttributes().put(StructLog.IS_ROOT, "false");
		getAttributes().put(StructLog.COMPONENT_NAME, "defaultComponentName");

	}

	private StructLog(String componentName, final boolean isRoot) {
		getAttributes().put(StructLog.IS_ROOT, String.valueOf(isRoot));
		getAttributes().put(StructLog.COMPONENT_NAME, componentName);
	}

	static StructLog getInstance(final String componentName,
			final boolean isRoot) {
		return new StructLog(componentName, isRoot);
	}

	public boolean isRoot() {
		return getAttributes().containsKey(StructLog.IS_ROOT)
				&& getAttributes().get(StructLog.IS_ROOT).equals("true");
	}

	public String getComponentName() {
		return getAttributes().get(StructLog.COMPONENT_NAME);
	}

	@Override
	public void close() {
		if (0 == endTimestamp) {
			endTimestamp = (new DateTime()).getMillis();
			validate();
			save();
		} else {
			// Fail fast, and catch early if close() gets called twice.
			throw new IllegalStateException("close() called twice.");
		}
	}

	@Override
	protected void save() {
		DaoFactory.getInstance().save(this);
	}

	@Override
	protected void validate() throws IllegalStateException {
		Validator validator;
		final ValidatorFactory factory = Validation
				.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		final Set<ConstraintViolation<StructLog>> validationResponse = validator
				.validate(this);
		final StringBuilder sb = new StringBuilder();
		for (final ConstraintViolation<StructLog> constraintViolation : validationResponse) {
			sb.append(constraintViolation.toString());
		}
		final String message = sb.toString();
		if (!StringUtils.isEmpty(message)) {
			throw new IllegalStateException(message);
		}
		return;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
