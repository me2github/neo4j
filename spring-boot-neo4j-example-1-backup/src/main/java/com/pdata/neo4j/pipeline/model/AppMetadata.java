package com.pdata.neo4j.pipeline.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AppMetadata {
	static Map<String, AppMetadata> mapData = new HashMap<>();

	static {
		mapData.put("1", AppMetadata.of("Application1", "1", "test", "IAMID", "INE1234", "INE1234", "zendesk1", "DB",
				"IAMID", "ID"));
		mapData.put("2", AppMetadata.of("Application2", "2", "test 2", "IAMID", "INE1235", "INE1235", "zendesk2", "API",
				"IAMID", "ID"));
	}

	private String applicationName;

	private String applicationUUID;

	private String businessDesc;

	private String ownerTypeIdentifier;

	private String businessOwner;

	private String backupBusinessOwner;

	private String zendeskName;

	private String connectionType;

	private String accountIdentifier;

	private String accessIdentifier;

	public static AppMetadata getAppMetaData(String applicationId) {
		return mapData.get(applicationId);
	}
}
