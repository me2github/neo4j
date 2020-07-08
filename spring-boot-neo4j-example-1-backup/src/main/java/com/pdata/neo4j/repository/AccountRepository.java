package com.pdata.neo4j.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pdata.neo4j.node.Account;

@Repository
public interface AccountRepository extends Neo4jRepository<Account, Long> {

    @Query("MATCH (a:Account) RETURN a")
    Collection<Account> getAllAccount();
    
    @Query("MATCH (a:Account)-[r:BELONGS_TO]->(acc: Access)  RETURN a, r, acc")
    List<Account> getAllAccount(@Param("accountId") String accountId);    
    

    @Query("MATCH (acc:Account)-[r:HAS_ACCESS]->(ass: Access)-[r2:BELONGS_TO]->(app:Application) WHERE acc.ACCOUNT_ID=$accountId RETURN acc, ass, app, r, r2")
    //@Query("MATCH (acc:Account)-[r:HAS_ACCESS]->(ass: Access) WHERE acc.ACCOUNT_ID=\"de39\" RETURN acc, ass,  r")
    List<Account> getAllAccessForGivenAccountId(@Param("accountId") String accountId);    
    
}
