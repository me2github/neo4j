package com.pdata.neo4j.node;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NodeEntity(label = "AccessN")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Access {
	
	@Id
	@GeneratedValue
	private Long id;

	@Index(unique = true)
	@Property(name = "ACCESS_ID")
	private String accessId;
	
	@Property(name = "LONG_DESC")
	private String longDesc;

	@Property(name = "SHORT_DESC")
	private String shortDesc;

	@Property(name = "ACCESS_NAME")
	private String accessName;

	@Property(name = "ACCESS_LEVEL_1_ID")
	private String accessLevel1Id;

	@Property(name = "APPLICATION_ID")
	private String applicationId;

	@Property(name = "ACCESS_TYPE")
	private String accessType;

	@Property(name = "ACCESS_OWNER")
	private String accessOwner;

	@Property(name = "UUID")
	private String uuid;

	
	@Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
	@JsonIgnoreProperties("access")
    private BelongToRelationShip belongToRelationShip;

	public static Access of(String accessId, String longDesc, String accessName, String accessLevel1Id,
			String applicationId, String accessType, String accessOwner) {
		Access access = new Access();
		access.setAccessId(accessId);
		access.setLongDesc(longDesc);
		access.setAccessName(accessName);
		access.setAccessLevel1Id(accessLevel1Id);
		access.setApplicationId(applicationId);
		access.setAccessType(accessType);
		access.setAccessOwner(accessOwner);
		
		return access;
	}
	
	//@Relationship(type = "HAS_ACCESS", direction = Relationship.INCOMING)
	//@JsonIgnoreProperties("access")
	//private HasAccessRelationShip hasAccessRelationShip;
	
	/*
	 * @Relationship(type = "HAS_ACCESS", direction = Relationship.INCOMING)
	 * 
	 * @JsonIgnoreProperties("access") private Account account;
	 */
	
}
