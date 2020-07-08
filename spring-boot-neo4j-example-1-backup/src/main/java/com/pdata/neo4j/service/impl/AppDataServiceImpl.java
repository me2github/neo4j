package com.pdata.neo4j.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.node.Account;
import com.pdata.neo4j.node.Application;
import com.pdata.neo4j.node.dto.AccessDTO;
import com.pdata.neo4j.node.dto.ApplicationDTO;
import com.pdata.neo4j.node.dto.BelongsToRelationshipDTO;
import com.pdata.neo4j.pipeline.NormalizeData;
import com.pdata.neo4j.pipeline.model.AppMetadata;
import com.pdata.neo4j.repository.AccessRepository;
import com.pdata.neo4j.repository.AccountRepository;
import com.pdata.neo4j.repository.ApplicationRepository;
import com.pdata.neo4j.repository.ApplicationRepositoryNeo;
import com.pdata.neo4j.repository.impl.ApplicationRepositoryImpl;
import com.pdata.neo4j.service.AppDataService;

@Service
public class AppDataServiceImpl implements AppDataService {

	@Autowired
	AccessRepository accessRepository;

	@Autowired
	ApplicationRepository appRepository;
	
	@Autowired
	AccountRepository accRepo;
	
	@Transactional(readOnly = true)
	public Collection<Access> getAll() {
		Collection<Access> aa = accessRepository.getAllAccess();
		return aa;
	}

	@Transactional(readOnly = true)
	public Iterable<Access> findAll() {
		return accessRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Iterable<Application> findAllApplication() {
		return appRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Access findByAccessName(String accessName) {
		return accessRepository.findByAccessName(accessName);
	}
	
	@Transactional(readOnly = true)
	public List<Access> getAllAccessWithAccessId(String accessId) {
		
		//List<Application> aaa = appRepository.getAllApplicationId(accessId);
		
		List<Access> aa = accessRepository.getAllAccessWithAccessId(accessId);
		return aa;
	}
	
	@Transactional(readOnly = true)
	public Iterable<Application> getAllApplicationWithAppId(String appId) {
		
		List<Application> aaa= appRepository.getAllApplicationId(appId);
		
		return aaa;
	}
	
	
	@Transactional(readOnly = true)
	public List<String> getAllAccessRelationship(Long accessId) {
		return accessRepository.getAllAccessRelationship(accessId);
	}
	
	
	
	@Transactional(readOnly = true)
	public Map<String, Object> getAllAccountInGraph(String accountId) {
		List<Account> accList = accRepo.getAllAccessForGivenAccountId(accountId);
		return toD3Format(accList);
	}
	
	
	
	private Map<String, Object> toD3Format(List<Account> movies) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Account> result = movies.iterator();
		while (result.hasNext()) {
			Account movie = result.next();
			nodes.add(map("title", movie.getAccountType(), "label", "movie"));
			int target = i;
			i++;
			Access access = movie.getHasAccessRelationShip().getAccess();
			
				Map<String, Object> actor = map("title", access.getAccessName(), "label", "actor");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	
	@Transactional
	public Application saveApplication(ApplicationDTO application) {
		Application app = appRepository.save(Application.of(application.getApplicationId(), 
				application.getAppName(), application.getAppShortName(), application.getAppType()));
		
		return app;
	}

	@Transactional
	public Access saveAccessData(AccessDTO access) {
		Access acc = accessRepository.save(Access.of(access.getAccessId(), access.getLongDesc(), access.getAccessName(), access.getAccessLevel1Id(), access.getApplicationId(), access.getAccessType(), access.getAccessOwner()));
		
		return acc;
	}
	
	@Transactional
	public String deleteApplication(String applicationId) {
		appRepository.deleteByApplicationId(applicationId);
		
		return applicationId;
	}

	public List<Access> getAllAccessBelongsToApplication(String query, String relationType, String firstSeen) {
		List<Access> accessList = appRepository.getAllAccessBelongsToApplication(query, relationType, firstSeen);
		return accessList;
	}
	
	@Autowired
	ApplicationRepositoryNeo repoNeo = new ApplicationRepositoryImpl();

	public List<Access> getAllAccessFilterOnRelationship(String applicationId, BelongsToRelationshipDTO relationship) {
		
		String firstSeen = relationship.getFirstSeen();
		String lastSeen = relationship.getLastSeen();
		String relationType = relationship.getRelationshipType();
		
		List<Access> accessList = appRepository.getAllAccessFilterOnRelationship(applicationId, firstSeen, lastSeen, relationType);
		return accessList;
	}
	
	@Override
	public void readAndSaveCSVData(MultipartFile readDataFile) {
		
		//to resolve the owner Type
		Map<String, AppMetadata> mapData = readAppMetaDataFromGraphDB("applicationId");
		
		List<Access> accessList = NormalizeData.normalizeAccessData(mapData);
		accessRepository.saveAll(accessList);
	}
	
	public Map<String, AppMetadata> readAppMetaDataFromGraphDB(String applicationId) {		
		Map<String, AppMetadata> mapData = new HashMap<>();
		
		
		return mapData;
	}
}
