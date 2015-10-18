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
	private static final int ID_LENGTH = 10;

	@Id
	private final String id = RandomStringUtils
			.randomAlphanumeric(StructLogPojo.ID_LENGTH);

	@Min(message = "startTimeStamp is invalid.", value = 1)
	private final long startTimestamp = (new DateTime()).getMillis();

	@Min(message = "Did not call Close() on the object.", value = 1)
	protected long endTimestamp = 0;

	// We started with Throwable and then later tried Exceptions, but both of
	// which are not getting stored in MongoDB.
	@NotNull
	private final List<String> exceptionsCaught = new ArrayList<String>();
	@NotNull
	private final List<StructLogPojo> dependentStructLog = new ArrayList<StructLogPojo>();
	@NotNull
	private final Map<String, String> attributes = new HashMap<String, String>();
	private String input = null;
	private String output = null;
	private boolean isRoot = false;
	/**
	 * Name of the component creating the log.
	 * <p>
	 * E.g. If ServiceCall1 is servicing a request for User1, then this variable
	 * name could be serviceCall1.
	 */
	private String name = "default";
	/**
	 * Name of the user for whom the request is being processed.
	 * <p>
	 * E.g. If ServiceCall1 is servicing a request for User1, then this variable
	 * name could be User1.
	 */
	private String user;

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

	/**
	 * Use only to get the List. To add individual Exception use addException()
	 * 
	 * @return
	 */
	public List<String> getExceptionsCaught() {
		return exceptionsCaught;
	}

	public void addExceptionCaught(String exceptionData) {
		getExceptionsCaught().add(exceptionData);
	}

	/**
	 * Use only to get the whole list. To add individual structLog, use
	 * addDependentStructLog.
	 * 
	 * @return
	 */
	public List<StructLogPojo> getDependentStructLog() {
		return dependentStructLog;
	}

	public void addDependentStructLog(StructLogPojo structLogPojo) {
		getDependentStructLog().add(structLogPojo);
	}

	/**
	 * Use only to get the whole Map. To add individual entry use addAttribute()
	 * 
	 * @return
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void addAttribute(String key, String value) {
		getAttributes().put(key, value);
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public long getEndTimestamp() {
		return endTimestamp;
	}

	abstract public void close();

	abstract protected void save();

	abstract protected void validate() throws IllegalStateException;

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime
				* result
				+ ((dependentStructLog == null) ? 0 : dependentStructLog
						.hashCode());
		result = prime * result + (int) (endTimestamp ^ (endTimestamp >>> 32));
		result = prime
				* result
				+ ((exceptionsCaught == null) ? 0 : exceptionsCaught.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + (isRoot ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		result = prime * result
				+ (int) (startTimestamp ^ (startTimestamp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StructLogPojo other = (StructLogPojo) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (dependentStructLog == null) {
			if (other.dependentStructLog != null)
				return false;
		} else if (!dependentStructLog.equals(other.dependentStructLog))
			return false;
		if (endTimestamp != other.endTimestamp)
			return false;
		if (exceptionsCaught == null) {
			if (other.exceptionsCaught != null)
				return false;
		} else if (!exceptionsCaught.equals(other.exceptionsCaught))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (isRoot != other.isRoot)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		if (startTimestamp != other.startTimestamp)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StructLogPojo [id=" + id + ", startTimestamp=" + startTimestamp
				+ ", endTimestamp=" + endTimestamp + ", exceptionsCaught="
				+ exceptionsCaught + ", dependentStructLog="
				+ dependentStructLog + ", attributes=" + attributes
				+ ", input=" + input + ", output=" + output + ", isRoot="
				+ isRoot + ", name=" + name + ", user=" + user + "]";
	}
}
