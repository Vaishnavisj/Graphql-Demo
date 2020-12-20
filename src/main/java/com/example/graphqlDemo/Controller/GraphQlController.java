package com.example.graphqlDemo.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphqlDemo.exception.SanitizedError;
import com.example.graphqlDemo.service.GraphqlService;

import graphql.ExceptionWhileDataFetching;
import graphql.ExecutionResult;
import graphql.GraphQLError;


@RequestMapping("/graphql/dept")
@RestController
public class GraphQlController {

	 private static final Logger logger = LoggerFactory.getLogger(GraphQlController.class);
		
	 @Autowired
	 private GraphqlService graphQLService;
	 
	 @PostMapping( "/getBooks")
	    public ResponseEntity<Object> getAllBooks(@RequestBody String query) throws Exception{
	        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
	        if(!execute.getErrors().isEmpty())
	        {
	        	GraphQlController.filterGraphQLErrors(execute.getErrors());
		        return new ResponseEntity<>(GraphQlController.filterGraphQLErrors(execute.getErrors()), HttpStatus.INTERNAL_SERVER_ERROR);

	        	
	        }
	        return new ResponseEntity<>(execute, HttpStatus.OK);
	    }

	 protected static List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
		    return errors.stream()
		            .filter(e -> e instanceof ExceptionWhileDataFetching)
		            .map(e -> e instanceof ExceptionWhileDataFetching ? new SanitizedError((ExceptionWhileDataFetching) e) : e)
		            .collect(Collectors.toList());
		}
}
