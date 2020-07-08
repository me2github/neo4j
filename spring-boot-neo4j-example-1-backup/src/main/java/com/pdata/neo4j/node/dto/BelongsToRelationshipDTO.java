package com.pdata.neo4j.node.dto;

import lombok.Data;

@Data
public class BelongsToRelationshipDTO {

	private String relationshipType;
	
	private String firstSeen;
	
	private String lastSeen;
}
