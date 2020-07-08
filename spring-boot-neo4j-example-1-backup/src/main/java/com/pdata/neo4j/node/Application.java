package com.pdata.neo4j.node;

import java.util.List;

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
@NodeEntity(label = "Application")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Application {
	@Id
	@GeneratedValue
	@Property(name = "ID")
	private Long id;

	@Index(unique = true)
	@Property(name = "APPLICATION_ID")
	private String applicationId;

	@Property(name = "APP_NAME")
	private String appName;

	@Property(name = "APP_SHORT_NAME")
	private String appShortName;

	@Property(name = "APP_TYPE")
	private String appType;

	@Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)

	@JsonIgnoreProperties("application")
	private List<Access> accessList;

	public static Application of(String applicationId, String appName, String appShortName, String appType) {
		Application app = new Application();
		app.setApplicationId(applicationId);
		app.setAppName(appName);
		app.setAppType(appType);
		app.setAppShortName(appShortName);
		
		return app;
	}

	// @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
	// private BelongToRelationShip belongToRelationShip;

}
