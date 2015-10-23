package com.amaranth.structlog.struct.util;

import org.codehaus.jackson.map.ObjectMapper;

public class SerDeserHelper {
	private final static ObjectMapper objectMapper = new ObjectMapper();
	public static <T> T getObjectFromJsonString(String jsonString,
			Class<T> classOfT) {
		try {
			return classOfT.cast(objectMapper.readValue(jsonString, classOfT));
		} catch (Exception e) {
			// FIXME: integrate logger
						e.printStackTrace();
						System.err.println("Couldn't serialize " + classOfT.getName());
		}
		return null;
	}

	public static <T> String toJsonString(Object dataObject, Class<T> classOfT) {
		String serializedObject = null;
		if (null == dataObject) {
			return serializedObject;
		}

		try {
			serializedObject = objectMapper.writeValueAsString(dataObject);
		} catch (Exception e) {
			// FIXME: integrate logger
			e.printStackTrace();
			System.err.println("Couldn't serialize " + classOfT.getName());
		} finally {
			if (null == serializedObject) {
				serializedObject = dataObject.toString()
						+ "[couldn't serialize " + classOfT.getName() + "]";
			}
		}
		return serializedObject;
	}
}
