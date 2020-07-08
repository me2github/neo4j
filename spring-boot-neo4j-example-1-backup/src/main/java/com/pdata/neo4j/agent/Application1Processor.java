package com.pdata.neo4j.agent;

import java.util.List;
import java.util.Map;

import com.pdata.neo4j.agent.dto.Application1;

public class Application1Processor implements AgentProcessor <Application1>{

	@Override
	public Application1 processEndSystemData() {
		//read data from end system.		
		List<Application1> appListData = Application1.testData();
		
		
		//fetch mapping data from metadata store
		Map<String, String> mapping = MetaDataMapping.getMappingForApplication1();
		
		
		//CSV Writer
		
		
		
		//Transfer CSV file to centralized server
		
		return null;
	}

}
