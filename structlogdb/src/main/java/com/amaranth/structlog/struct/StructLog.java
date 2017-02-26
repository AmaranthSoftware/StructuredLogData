package com.amaranth.structlog.struct;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;

import com.amaranth.structlog.db.DaoFactory;
import com.amaranth.structlog.struct.util.SerDeserHelper;

public class StructLog extends StructLogPojo implements AutoCloseable {
	public static final String COLNAME_startTimestamp = "startTimestamp";
	public static final String COLNAME_name = "componentName";
	public static final String COLNAME_user = "userId";

	private StructLog() {
	}

	private StructLog(String componentName, final boolean isRoot) {
		setRoot(isRoot);
		setComponentName(componentName);
	}

	static StructLog getInstance(final String componentName,
			final boolean isRoot) {
		return new StructLog(componentName, isRoot);
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
		if (isRoot()) {
			DaoFactory.getInstance().save(this);
		}
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

	public void setInputAsObject(Object request) {
		setInput(SerDeserHelper.toJsonString(request, request.getClass()));
	}

	public void setOutputAsObject(Object output) {
		setOutput(SerDeserHelper.toJsonString(output, output.getClass()));
	} 

	public void addExceptionCaught(Throwable t) {
		String rootCauseStackTrace = null;

		rootCauseStackTrace = getCauseStackTrace(t);

		// FIXME: Ability to accept Throwable serializer
		getExceptionsCaught().add(
				ExceptionUtils.getMessage(t)
						+ ExceptionUtils.getFullStackTrace(t)
						+ ExceptionUtils.getRootCauseMessage(t)
						+ rootCauseStackTrace);
	}

	private String getCauseStackTrace(Throwable t) {
		if (null == t) {
			return null;
		}

		String rootCauseStackTrace;
		StringBuilder sb = new StringBuilder();
		// ExceptionUtils.getRootCauseStackTrace(t)
		for (String causeLine : ExceptionUtils.getRootCauseStackTrace(t)) {
			sb.append(causeLine);
		}
		rootCauseStackTrace = sb.toString();
		return rootCauseStackTrace;
	}
}
