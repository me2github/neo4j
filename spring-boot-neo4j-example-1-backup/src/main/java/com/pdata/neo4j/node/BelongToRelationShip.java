package com.pdata.neo4j.node;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@RelationshipEntity(type = "BELONGS_TO")
public class BelongToRelationShip {
	@Id 
	//@GeneratedValue
    private Long id;
	
	private List<String> belongsTo = new ArrayList<>();
	
	@StartNode
    private Access access;
	
    @EndNode
    private Application application;
}
