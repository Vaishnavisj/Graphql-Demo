package com.example.graphqlDemo.service.dataFetcher;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphqlDemo.entity.Department;
import com.example.graphqlDemo.repository.DepartmentRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllDepartmentsDataFetcher implements DataFetcher<List<Department>>{
	
	  @Autowired
	  DepartmentRepository departmentRepository;

	@Override
	public List<Department> get(DataFetchingEnvironment environment) {
		
		return departmentRepository.findAll();
	}

}
