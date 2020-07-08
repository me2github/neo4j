package com.pdata.neo4j.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.node.Application;
import com.pdata.neo4j.node.dto.AccessDTO;
import com.pdata.neo4j.node.dto.ApplicationDTO;
import com.pdata.neo4j.node.dto.BelongsToRelationshipDTO;

@Service
public interface AppDataService {

	public Collection<Access> getAll();

	public Iterable<Access> findAll();

	public Iterable<Application> findAllApplication();

	public Access findByAccessName(String accessName);

	public List<Access> getAllAccessWithAccessId(String accessId);

	public Iterable<Application> getAllApplicationWithAppId(String appId);

	public List<String> getAllAccessRelationship(Long accessId);

	public Map<String, Object> getAllAccountInGraph(String accountId);

	public Application saveApplication(ApplicationDTO application);

	public Access saveAccessData(AccessDTO access);

	public String deleteApplication(String applicationId);

	public List<Access> getAllAccessBelongsToApplication(String query, String relationType, String firstSeen);

	public List<Access> getAllAccessFilterOnRelationship(String applicationId, BelongsToRelationshipDTO relationship);

}
