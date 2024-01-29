package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.services.EmployeeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  // get all employees
  @GetMapping()
  @ResponseBody
  public List<EmployeeResponseDto> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  // create employee rest api
  @PostMapping("")
  @ResponseBody
  public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto employee) {
    EmployeeResponseDto createdEmployee = employeeService.createEmployee(employee);
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
  }

  // get employee by id rest api
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  // update employee rest api
  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<EmployeeResponseDto> updateEmployee(
          @PathVariable Long id, @RequestBody EmployeeRequestDto employeeDetails) {
    return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDetails));
  }

  // delete employee rest api
  @DeleteMapping("/{id}")
  @ResponseBody
  public Boolean deleteEmployee(@PathVariable Long id) {
    return employeeService.deleteEmployee(id);
  }
}