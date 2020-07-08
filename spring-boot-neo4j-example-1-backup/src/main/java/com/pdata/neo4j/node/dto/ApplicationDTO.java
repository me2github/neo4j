package com.pdata.neo4j.node.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ApplicationDTO {

	@JsonProperty(value = "applicationId")
	private String applicationId;

	@JsonProperty(value = "applicationName")
	private String appName;

	@JsonProperty(value = "applicationShortName")
	private String appShortName;

	@JsonProperty(value = "applicationType")
	private String appType;
		
		
}
