package com.pdata.neo4j.repository.impl;

import java.util.List;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.node.dto.BelongsToRelationshipDTO;
import com.pdata.neo4j.repository.ApplicationRepositoryNeo;

public class ApplicationRepositoryImpl implements ApplicationRepositoryNeo {

	@Override
	public List<Access> getAllAccessFilterOnRelationship(String applicationId, BelongsToRelationshipDTO relationship) {
		/*
		 * ​DatabaseManagementService managementService = new
		 * DatabaseManagementServiceBuilder( databaseDirectory ).build();
		 * ​GraphDatabaseService db = managementService.database( DEFAULT_DATABASE_NAME
		 * );
		 * 
		 * try ( Transaction tx = db.beginTx(); Result result = tx.execute(
		 * "MATCH (n {name: 'my node'}) RETURN n, n.name" ) ) { while ( result.hasNext()
		 * ) { Map<String,Object> row = result.next(); for ( Entry<String,Object> column
		 * : row.entrySet() ) { rows += column.getKey() + ": " + column.getValue() +
		 * "; "; } rows += "\n"; } }
		 */
		return null;
	}

}
