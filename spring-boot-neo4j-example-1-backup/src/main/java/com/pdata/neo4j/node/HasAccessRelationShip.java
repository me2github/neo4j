package com.pdata.neo4j.node;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@RelationshipEntity(type = "HAS_ACCESS")
public class HasAccessRelationShip {
	@Id 
	//@GeneratedValue
    private Long id;
	
	private List<String> hasAccess = new ArrayList<>();
	
	@StartNode
    private Account account;
	
    @EndNode
    private Access access;
}
