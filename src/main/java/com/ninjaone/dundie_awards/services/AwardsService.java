package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.messaging.dto.RestoreAwardRequestDto;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.messaging.event.NewActivityEvent;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
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
  private EmployeeRepository employeeRepository;
  @Autowired
  private MessageBroker messageBroker;

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
    var employees = employeeRepository.findByOrganizationId(organizationId);
    if (employees.isEmpty()) {
      throw new ResourceNotFoundException("Employees not found for organization: " + organizationId);
    }

    List<Employee> initialStateEmployees = employees.stream()
            .map(Employee::clone)
            .collect(Collectors.toList());

    employees.forEach(
            employee -> employee.setDundieAwards(employee.getDundieAwards() + numberOfAwards));

    var updatedEmployees = employeeRepository.saveAll(employees);

    messageBroker.publishEvent(
            new NewActivityEvent(
                    "Granting %d awards to organization:  %d".formatted(numberOfAwards, organizationId),
                    initialStateEmployees
            )
    );

    updateTotalAwards();

    return updatedEmployees.stream().map(EmployeeResponseDto::new).toList();
  }


  @Transactional
  public void restore(RestoreAwardRequestDto restoreAwardRequestDto) {
    employeeService.bulkUpdateEmployees(restoreAwardRequestDto.employees());
    awardsCache.setTotalAwards(calculateTotalAwards());
  }
}
