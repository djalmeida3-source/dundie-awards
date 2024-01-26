package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  public List<EmployeeResponseDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
            .map(EmployeeResponseDto::new)
            .toList();
  }

  public EmployeeResponseDto createEmployee(EmployeeRequestDto employee) {
    var organization = organizationRepository.findById(employee.getOrganizationId())
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

    Employee employeeEntity = new Employee();
    employeeEntity.updateFromDto(employee);
    employeeEntity.setOrganization(organization);

    return new EmployeeResponseDto(employeeRepository.save(employeeEntity));
  }

  public EmployeeResponseDto getEmployeeById(Long id) {
    var optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      return new EmployeeResponseDto(optionalEmployee.get());
    } else {
      throw new NoSuchElementException("Employee not found with id " + id);
    }
  }

  public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employee) {
    var optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      var employeeEntity = optionalEmployee.get();
      employeeEntity.updateFromDto(employee);
      return new EmployeeResponseDto(employeeRepository.save(employeeEntity));
    } else {
      throw new NoSuchElementException("Employee not found with id " + id);
    }
  }

  public Map<String, Boolean> deleteEmployee(Long id) {
    var optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      employeeRepository.delete(optionalEmployee.get());
      return Map.of("deleted", true);
    } else {
      throw new ResourceNotFoundException("Employee not found with id " + id);
    }
  }
}
