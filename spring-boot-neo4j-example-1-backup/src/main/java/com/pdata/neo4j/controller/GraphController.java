package com.pdata.neo4j.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdata.neo4j.node.Access;
import com.pdata.neo4j.node.Account;
import com.pdata.neo4j.node.Application;
import com.pdata.neo4j.node.dto.ApplicationDTO;
import com.pdata.neo4j.node.dto.BelongsToRelationshipDTO;
import com.pdata.neo4j.repository.AccountRepository;
import com.pdata.neo4j.service.AppDataService;
import com.pdata.neo4j.service.impl.AppDataServiceImpl;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController("/rest")
public class GraphController {

	@Autowired
	AppDataService appDataService = new AppDataServiceImpl();

	@Autowired
	private AccountRepository repo;
	
	@Value("classpath:access.graphqls")
	private Resource schemaResource;

	private GraphQL graphQL;

	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildWiring() {
		DataFetcher<List<Access>> fetcher1 = data -> {
			return (List<Access>) appDataService.getAll();
		};

		DataFetcher<Access> fetcher2 = data -> {
			return appDataService.findByAccessName((String) data.getArgument("accessName"));
		};

		DataFetcher<List<Access>> fetcher3 = data -> {
			return appDataService.getAllAccessWithAccessId((String) data.getArgument("accessId"));
		};

		
		DataFetcher<List<String>> fetcher4 = data -> {
			return appDataService.getAllAccessRelationship((Long) data.getArgument("accessId"));
		};

		DataFetcher<Iterable<Application>> fetcher5 = data -> {
			return appDataService.getAllApplicationWithAppId((String) data.getArgument("appId"));
		};

		DataFetcher<List<Access>> fetcher6 = data -> {
			return appDataService.getAllAccessBelongsToApplication((String) data.getArgument("applicationId"),
					(String)data.getArgument("relationType"), (String)data.getArgument("firstSeen"));
		};

		DataFetcher<List<Access>> fetcher7 = data -> {
			return appDataService.getAllAccessFilterOnRelationship((String) data.getArgument("applicationId"),
					(BelongsToRelationshipDTO)data.getArgument("relationship"));
		};
		
		
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWriting -> typeWriting
				.dataFetcher("findAllAccess_G", fetcher1)
				.dataFetcher("findByAccessName", fetcher2)
				.dataFetcher("findAllAccessWithAccessId", fetcher3)
				.dataFetcher("findAllAccessRelationship", fetcher4)
				.dataFetcher("findApplicationById", fetcher5)
				.dataFetcher("getAllAccessBelongsToApplication", fetcher6)
				.dataFetcher("getAllAccessFilterOnRelationship", fetcher7)
				)
				.build();

	}

	@GetMapping("/getAllAccessForGivenAccountId")
	public Collection<Account> getAllAccessForGivenAccountId(@RequestParam String accountId) {
		Collection<Account> aa = repo.getAllAccessForGivenAccountId(accountId);
		return  aa;
	}

	
	@PostMapping("/findAllAccess")
	public ResponseEntity<Object> getAll(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@PostMapping("/findByAccessName")
	public ResponseEntity<Object> findByAccessName(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@PostMapping("/findByAccessById")
	public ResponseEntity<Object> findByAccessById(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@PostMapping("/findByAccessRelatiopnship")
	public ResponseEntity<Object> findByAccessRelatiopnship(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@PostMapping("/findApplicationById")
	public ResponseEntity<Object> findApplicationById(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	

	@PostMapping("/saveApplicationData")
	private Application saveApplication(@RequestBody ApplicationDTO application){
		return appDataService.saveApplication(application);
	}
	
	@DeleteMapping("/deleteApplicationNodeById")
	private ResponseEntity<String> deleteApplicationNodeById(@RequestParam String applicationId){
		String id= appDataService.deleteApplication(applicationId);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
	
	@PostMapping("/getAllAccessBelongsToApplication")
	private ResponseEntity<Object> getAllAccessBelongsToApplication(@RequestBody String query){
		//return appDataService.getAllAccessBelongsToApplication(query);
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/getAllAccessFilterOnRelationship") private
	 * ResponseEntity<Object> getAllAccessFilterOnRelationship(@RequestBody String
	 * query){ ExecutionResult result = graphQL.execute(query); return new
	 * ResponseEntity<Object>(result, HttpStatus.OK); }
	 */
	
	
	@GetMapping("/readAndSaveAccessCSVData")
	private ResponseEntity<Object> readAndSaveCSVData() {
		appDataService.readAndSaveCSVData(null);
		return new ResponseEntity<Object>("Data is saved in graphDB", HttpStatus.OK);
	}
}
