package com.samco.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.samco.model.Employee;
import com.samco.repository.EmployeeRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
		List<Employee> employee = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
		parseAllRecords.forEach(record -> {
			Employee employees = new Employee();
			employees.setId(record.getInt("id"));
			employees.setName(record.getString("name"));
			employees.setAge(record.getInt("age"));
			employees.setCity(record.getString("city"));
			employees.setState(record.getString("state"));
			employee.add(employees);
		});
		employeeRepository.saveAll(employee);
		return "Created Successfully...";
	}

	@GetMapping("/getEmployee")
	public List<Employee> getEmployee() {
		return employeeRepository.findAll();
	}

	@GetMapping("/getEmployee/{id}")
	public Optional<Employee> getByid(@PathVariable int id) {
		return employeeRepository.findById(id);
	}

	@DeleteMapping("/delete")
	public void deleteAll(Employee employee) {
		employeeRepository.deleteAll();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteByid(@PathVariable int id) {
		employeeRepository.deleteById(id);
	}

}
