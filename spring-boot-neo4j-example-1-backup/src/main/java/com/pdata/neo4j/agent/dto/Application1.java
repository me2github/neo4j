package com.pdata.neo4j.agent.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Application1 {

	private String id;

	private String name;

	
	public static List<Application1> testData() {
		List<Application1> appList = new ArrayList<>();
		appList.add(Application1.of("1234", "Access1"));
		appList.add(Application1.of("1235", "Access2"));
		appList.add(Application1.of("1236", "Access3"));
		appList.add(Application1.of("1237", "Access4"));
		
		return appList; 
	}
}
