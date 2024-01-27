package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.config.Publisher;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.model.Employee;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AwardsService {

  @Autowired
  private AwardsCache awardsCache;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private Publisher publisher;

  public int calculateTotalAwards() {
    return employeeService.getAllEmployees().stream()
      .mapToInt(employee -> Objects.requireNonNullElse(employee.getDundieAwards(), 0))
      .sum();
  }

  public void updateTotalAwards() {
    awardsCache.setTotalAwards(calculateTotalAwards());
  }

  @Transactional
  public List<EmployeeResponseDto> grantAwardByOrganization(Long organizationId, int numberOfAwards) {
    var employees = employeeService.getEmployeesByOrganization(organizationId);
    if (employees.isEmpty()) {
      throw new ResourceNotFoundException("Employees not found for organization: " + organizationId);
    }

    List<Employee> initialStateEmployees = employees.stream()
            .map(Employee::clone)
            .collect(Collectors.toList());

    employees.forEach(
            employee -> employee.setDundieAwards(employee.getDundieAwards() + numberOfAwards));

    var updatedEmployees = employeeService.updateEmployees(employees);

    Activity activity = new Activity(
            LocalDateTime.now(),
            "Granting " + numberOfAwards + " awards to organization:  " + organizationId);

    publisher.publishEvent(activity);

    updateTotalAwards();

    return updatedEmployees.stream().map(EmployeeResponseDto::new).toList();
  }


}
