package com.samco.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.samco.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer>{

}
