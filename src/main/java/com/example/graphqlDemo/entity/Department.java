package com.example.graphqlDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@Table
@Entity
public class Department {
	
	public Department(String departmentId, String name) {
		this.departmentId= departmentId;
		this.name=name;
	}

	@Id
	private String departmentId;
	

	public Department() {
		super();
	}

	private String name;

}
