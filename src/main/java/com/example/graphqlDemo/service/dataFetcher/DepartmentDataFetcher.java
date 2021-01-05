package com.example.graphqlDemo.service.dataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphqlDemo.entity.Department;
import com.example.graphqlDemo.repository.DepartmentRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DepartmentDataFetcher implements DataFetcher<Department>{

	 @Autowired
	  DepartmentRepository departmentRepository;
	 
	@Override
	public Department get(DataFetchingEnvironment environment) {
		String id = environment.getArgument("departmentId");
		return departmentRepository.findById(id).get();
	}

}
