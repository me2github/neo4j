package com.pdata.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.node.Application;

@Repository
public interface ApplicationRepository extends Neo4jRepository<Application, Long> {
    
    @Query("MATCH (a:Application), (app:Access) where a.APPLICATION_ID=$appId  and app.APPLICATION_ID=$appId  RETURN a, app")
    List<Application> getAllApplicationId(@Param("appId") String appId);

    @Query("MATCH (a: Application) where a.APPLICATION_ID = $applicationId DETACH DELETE a")
	void deleteByApplicationId(@Param("applicationId") String applicationId);

    @Query("MATCH(acc:Access)-[r:BELONGS_TO]->(app:Application) where type(r) = $relationType and r.first_seen = $firstSeen and app.APPLICATION_ID=$applicationId return acc")
	List<Access> getAllAccessBelongsToApplication(@Param("applicationId") String applicationId, @Param("relationType") String relationType, 
			@Param("firstSeen") String firstSeen);

    @Query("MATCH(acc:Access)-[r:BELONGS_TO]->(app:Application) where type(r) = $relationType and r.first_seen=$firstSeen and r.last_seen=$lastSeen  and acc.ACCESS_ID=\"FE090EB7-5D76-4085-B8C3-C7F64A1AC06A\" return acc, r")
	List<Access> getAllAccessFilterOnRelationship(String applicationId, String firstSeen, String lastSeen,
			String relationType);
	    
}
