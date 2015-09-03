package com.amaranth.structlog.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public abstract class StructLogPojo {
	@Id
	private final String id = RandomStringUtils.randomAlphanumeric(StructLogPojo.ID_LENGTH);
	@Min(message = "startTimeStamp is invalid.", value = 1)
	private final long startTimestamp = (new DateTime()).getMillis();
	@Min(message = "Did not call Close() on the object.", value = 1)
	protected long endTimestamp = 0;
	@NotNull
	private final List<Throwable> exceptionsCaught = new ArrayList<Throwable>();
	@NotNull
	private final List<StructLogPojo> dependentStructLog = new ArrayList<StructLogPojo>();
	@NotNull
	private final Map<String, String> attributes = new HashMap<String, String>();
	private String input = null;
	private String output = null;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getId() {
		return id;
	}

	public List<Throwable> getExceptionsCaught() {
		return exceptionsCaught;
	}

	public List<StructLogPojo> getDependentStructLog() {
		return dependentStructLog;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public long getEndTimestamp() {
		return endTimestamp;
	}
	private static final int ID_LENGTH = 10;

	abstract public void close();

	abstract protected void save();

	abstract protected void validate() throws IllegalStateException;

	@Override
	public String toString() {
		return "StructLogPojo [id=" + id + ", startTimestamp=" + startTimestamp
				+ ", endTimestamp=" + endTimestamp + ", exceptionsCaught="
				+ exceptionsCaught + ", dependentStructLog="
				+ dependentStructLog + ", attributes=" + attributes
				+ ", input=" + input + ", output=" + output + "]";
	}
	
}
