package com.pdata.neo4j.node.dto;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NodeEntity(label = "Access")
@NoArgsConstructor
public class AccessDTO {
	
	private Long id;

	private String accessId;
	
	private String longDesc;

	private String accessName;

	private String accessLevel1Id;

	private String applicationId;

	private String accessType;

	private String accessOwner;

}
