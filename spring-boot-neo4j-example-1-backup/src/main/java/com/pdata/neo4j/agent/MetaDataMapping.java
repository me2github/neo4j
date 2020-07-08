package com.pdata.neo4j.agent;

import java.util.HashMap;
import java.util.Map;

public class MetaDataMapping {
	
	static Map<String, String> mapping = new HashMap<>();
	
	public static Map<String, String> getMappingForApplication1() {
		mapping.put("ID", "ACCESS_ID");
		mapping.put("NAME", "ACCESS_NAME");
		
		return mapping;
	}
}
