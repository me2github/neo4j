package com.pdata.neo4j.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pdata.neo4j.node.Access;

//@RepositoryRestResource(collectionResourceRel = "AccessN", path = "Access")
@Repository
public interface AccessRepository extends Neo4jRepository<Access, Long> {

    @Query("MATCH (a:Access) RETURN a")
    Collection<Access> getAllAccess();
    
	/*
	 * @Query(value = "MATCH (a:User),(b:Report)\n" +
	 * "WHERE a.username = :#{#username} AND ID(b) = :#{#reportId}\n" +
	 * "CREATE (a)-[r:REPORT]->(b)")
	 * 
	 * @Transactional void createReportRelationship(@Param("username") String
	 * username, @Param("reportId") Long reportId);
	 */
    @Query("MATCH (a:Access) WHERE a.ACCESS_NAME = $accessName  RETURN a")
	Access findByAccessName(@Param("accessName") String accessName);
    
    @Query("MATCH (a:Access)-[r:BELONGS_TO]->(app: Application)  RETURN a, app, r")
    List<Access> getAllAccessWithAccessId(@Param("accessId") String accessId);    
    
    @Query("MATCH (a:Access)-[r]->(Application) RETURN type(r)")
    List<String> getAllAccessRelationship(Long accessId);
}
