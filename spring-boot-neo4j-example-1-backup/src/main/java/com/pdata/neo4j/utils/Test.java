package com.pdata.neo4j.utils;

import java.util.ArrayList;
import java.util.List;

import com.pdata.neo4j.node.Access;

public class Test {

	public static void main(String[] s) {
		String file = "src/main/resources/Access.csv";
		List<String> ord = new ArrayList<String>();
		ord.add("accessLevel1Id");
		ord.add("accessId");
		ord.add("accessName");
		ord.add("accessOwner");
		ord.add("shortDesc");
		ord.add("longDesc");
		
		ord.add("accessType");
		ord.add("applicationId");
		//ord.add("COLLECTION_DATE");
		
		
		CustomCsvReader<Access> reader = new CustomCsvReader<Access>(Access.class, file, true)
				.setOrder(ord)
				.read()
				.process(new AccessCsvProcessor());
		
		for(Access msg :reader.getData()) {
			System.out.println(msg);
		}	
	}
}
