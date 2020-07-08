package com.pdata.neo4j.utils;

import com.pdata.neo4j.node.Access;

public class AccessCsvProcessor implements CsvProcessor<Access> {

	@Override
	public Access process(Access inData) {
		inData.setAccessId(inData.getAccessId().toUpperCase());
		return inData;
	}	
}