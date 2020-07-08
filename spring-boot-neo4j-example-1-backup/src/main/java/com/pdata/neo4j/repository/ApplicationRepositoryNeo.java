package com.pdata.neo4j.repository;

import java.util.List;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.node.dto.BelongsToRelationshipDTO;

public interface ApplicationRepositoryNeo {
	
	List<Access> getAllAccessFilterOnRelationship(String applicationId, BelongsToRelationshipDTO relationship);
}
