package com.pdata.neo4j.pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.pipeline.model.AppMetadata;
import com.pdata.neo4j.utils.AccessCsvProcessor;
import com.pdata.neo4j.utils.CustomCsvReader;

public class NormalizeData {

	// For testing only
	public static void main(String[] s) {
		List<Access> accessList = NormalizeData.readCSV();

		accessList.forEach(item -> {
			String uuid = NormalizeData.getPeopleDataFromPData(item.getAccessOwner(), null);
			item.setUuid(uuid);
		});
	}

	public static List<Access> normalizeAccessData(Map<String, AppMetadata> mapData) {
		List<Access> accessList = NormalizeData.readCSV();

		accessList.forEach(item -> {
			String uuid = NormalizeData.getPeopleDataFromPData(item.getAccessOwner(), mapData);
			item.setUuid(uuid);
		});

		return accessList;
	}

	public static List<Access> readCSV() {
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

		CustomCsvReader<Access> reader = new CustomCsvReader<Access>(Access.class, file, true).setOrder(ord).read()
				.process(new AccessCsvProcessor());

		for (Access msg : reader.getData()) {
			System.out.println(msg);
		}

		return reader.getData();

	}

	public static String getPeopleDataFromPData(String owner, Map<String, AppMetadata> mapData) {

		// feignCline to fetch data from pdata endpoint. and resolve as per AppMetaData.

		String uuid = "123456789";
		if (owner != null) {

		}

		return uuid;
	}

	public Map<String, AppMetadata> readAppMetaDataFromGraphDB(String applicationId) {

		Map<String, AppMetadata> mapData = new HashMap<>();

		return mapData;
	}

}
