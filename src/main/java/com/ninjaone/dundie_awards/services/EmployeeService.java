package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private OrganizationRepository organizationRepository;
  @Autowired
  private ActivityService activityService;
  @Autowired
  private ApplicationEventPublisher eventPublisher;

  private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

  public List<EmployeeResponseDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
            .map(EmployeeResponseDto::new)
            .toList();
  }

  @Transactional
  public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
    var employee = employeeRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());

    if (employee.isPresent()) {
      throw new IllegalArgumentException("Employee already exists");
    }

    var organization = organizationRepository.findById(dto.getOrganizationId())
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

    Employee employeeEntity = new Employee();
    employeeEntity.updateFromDto(dto);
    employeeEntity.setOrganization(organization);

    logger.info("Employee created successful -> id:{} firstName:{} lastName:{}",
            employeeEntity.getId(), employeeEntity.getFirstName(), employeeEntity.getLastName());

    var createdEmployee = new EmployeeResponseDto(employeeRepository.save(employeeEntity));

    return createdEmployee;
  }

  public EmployeeResponseDto getEmployeeById(Long id) {
    var optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      return new EmployeeResponseDto(optionalEmployee.get());
    } else {
      throw new NoSuchElementException("Employee not found with id " + id);
    }
  }

  @Transactional
  public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employee) {
    var optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      var employeeEntity = optionalEmployee.get();
      employeeEntity.updateFromDto(employee);
      var updatedEmployee = new EmployeeResponseDto(employeeRepository.save(employeeEntity));
      return updatedEmployee;
    } else {
      throw new NoSuchElementException("Employee not found with id " + id);
    }
  }

  @Transactional
  public Map<String, Boolean> deleteEmployee(Long id) {
    var optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isPresent()) {
      employeeRepository.delete(optionalEmployee.get());
      return Map.of("deleted", true);
    } else {
      throw new ResourceNotFoundException("Employee not found with id " + id);
    }
  }

  public List<Employee> getEmployeesByOrganization(Long organizationId) {
    return employeeRepository.findByOrganizationId(organizationId);
  }

  public List<Employee> updateEmployees(List<Employee> employees) {
    var updatedEmployees = employeeRepository.saveAll(employees);
    return updatedEmployees;
  }

}
