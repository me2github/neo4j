package com.pdata.neo4j.node;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NodeEntity(label = "Account")
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	@Property(name = "ACCESS_ID_1")
	private String accessId1;

	@Property(name = "ACCESS_ID_2")
	private String accessId2;

	@Property(name = "ACCESS_ID_3")
	private String accessId3;

	@Property(name = "APPLICATION_ID")
	private String applicationId;

	@Property(name = "ACCOUNT_TYPE")
	private String accountType;

	@Property(name = "ACCOUNT_ID")
	private String accountId;

	@Property(name = "ACCESS_NAME")
	private String accessName;

	@Property(name = "COLLECTION_DATE")
	private String colllectionDate;

	@Property(name = "USER_ID")
	private String userId;

	@Property(name = "ACCESS_ACCOUNT_ID")
	private String accessAccountId;

	@Property(name = "ACCESS_STATUS")
	private String accessStatus;

	@Property(name = "CAS5")
	private String cas5;

	/*
	 * @Relationship(type = "HAS_ACCESS", direction = Relationship.OUTGOING)
	 * 
	 * @JsonIgnoreProperties("account") private List<Access> access;
	 */

	@Relationship(type = "HAS_ACCESS", direction = Relationship.OUTGOING)
	@JsonIgnoreProperties("account")
	private HasAccessRelationShip hasAccessRelationShip;

}
