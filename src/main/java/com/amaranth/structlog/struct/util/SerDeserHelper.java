package com.amaranth.structlog.struct.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerDeserHelper {

	// FIXME: Replace with jackson object mapper.
	private static final Gson gson = new GsonBuilder().create();
	
	public static <T> T getObjectFromJsonString(String jsonString, Class<T> classOfT) 
	{
		return  classOfT.cast(gson.fromJson(jsonString, classOfT));
	}
	
	public static <T> String toJsonString(Object o, Class<T> classOfT) 
	{
		return gson.toJson(o, classOfT);
	}
}
