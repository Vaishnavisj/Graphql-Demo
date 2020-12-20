package com.example.graphqlDemo.service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.graphqlDemo.entity.Department;
import com.example.graphqlDemo.repository.DepartmentRepository;
import com.example.graphqlDemo.service.dataFetcher.AllDepartmentsDataFetcher;
import com.example.graphqlDemo.service.dataFetcher.DepartmentDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphqlService {
	
	 private static final Logger logger = LoggerFactory.getLogger(GraphqlService.class);
		

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Value("classpath:departments.graphql")
    private Resource resource;
	
	private GraphQL graphQL;
	
	@Autowired
    private AllDepartmentsDataFetcher allDepartmentsDataFetcher;
    @Autowired
    private DepartmentDataFetcher departmentDataFetcher;
    
  
	  @PostConstruct
	  public void type() throws IOException {
		  logger.info("loading data into HSQL"); 
		  Department dept = new Department("1","ABC");
		  departmentRepository.save(dept); 
			
			  File schemaFile = resource.getFile();
			  logger.info("loading data into HSQL"+schemaFile); 
			  TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile); RuntimeWiring wiring =
			  buildRuntimeWiring(); GraphQLSchema schema = new
			  SchemaGenerator().makeExecutableSchema(typeRegistry, wiring); graphQL =
			  GraphQL.newGraphQL(schema).build();
			 
	  }
	  
	  
	  
	  
		/*
		 * public void loadSchema() throws IOException { loadDataIntoHSQL();
		 * logger.info("loading data into HSQL"); File schemaFile = resource.getFile();
		 * logger.info("loading data into HSQL"+schemaFile); TypeDefinitionRegistry
		 * typeRegistry = new SchemaParser().parse(schemaFile); RuntimeWiring wiring =
		 * buildRuntimeWiring(); GraphQLSchema schema = new
		 * SchemaGenerator().makeExecutableSchema(typeRegistry, wiring); graphQL =
		 * GraphQL.newGraphQL(schema).build(); }
		 */
	 
	  
	  private void loadDataIntoHSQL() { Stream.of( new Department("1", "CSE"), new
	  Department("2", "ENTC"), new Department("3", "IT") ).forEach(department -> {
	  departmentRepository.save(department); });
		  
		  logger.info("repo"+departmentRepository);
	  
	  }
	  
	  private RuntimeWiring buildRuntimeWiring() { return
	  RuntimeWiring.newRuntimeWiring() .type("Query", typeWiring -> typeWiring
	  .dataFetcher("allDepartments", allDepartmentsDataFetcher)
	  .dataFetcher("department", departmentDataFetcher)) .build(); }
	  
	  
	  public GraphQL getGraphQL() { return graphQL; }
	 

}
