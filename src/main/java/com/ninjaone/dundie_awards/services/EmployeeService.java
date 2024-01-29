package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.exception.ResourceValidationException;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private OrganizationRepository organizationRepository;

  @Autowired
  private EmployeeMapper employeeMapper;

  private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

  public List<EmployeeResponseDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
            .map(EmployeeResponseDto::new)
            .toList();
  }

  @Transactional
  public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

    logger.info("Creating a new Employee -> {}", dto.toString());

    var employee = employeeRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());

    if (employee.isPresent()) {
      throw new ResourceValidationException("Employee already exists");
    }

    var organization = organizationRepository.findById(dto.getOrganizationId())
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

    var newEmployee = employeeMapper.mapToEntity(dto);
    newEmployee.setOrganization(organization);

    return employeeMapper.mapToDto(employeeRepository.save(newEmployee));
  }

  public EmployeeResponseDto getEmployeeById(Long id) {
    var employee = employeeRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    return this.employeeMapper.mapToDto(employee);
  }

  @Transactional
  public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
    var currentEmployee = employeeRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));

    var organization = organizationRepository.findById(dto.getOrganizationId())
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

    currentEmployee.setFirstName(dto.getFirstName());
    currentEmployee.setLastName(dto.getLastName());
    currentEmployee.setDundieAwards(dto.getDundieAwards());
    currentEmployee.setOrganization(organization);

    return employeeMapper.mapToDto(employeeRepository.save(currentEmployee));
  }

  @Transactional
  public Boolean deleteEmployee(Long id) {
    var currentEmployee = employeeRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    employeeRepository.delete(currentEmployee);
    return true;
  }

  public List<EmployeeResponseDto> bulkUpdateEmployees(List<Employee> employees) {
    return this.employeeMapper.mapToDto(employeeRepository.saveAll(employees));
  }

}
