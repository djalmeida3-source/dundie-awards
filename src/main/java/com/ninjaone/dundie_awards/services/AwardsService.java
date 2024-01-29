package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.messaging.event.NewActivityEvent;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
  @Autowired
  private EmployeeMapper employeeMapper;

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

    Map<Long, EmployeeResponseDto> initialStateEmployees = new HashMap<>();

    employees.forEach(
            employee -> {
              initialStateEmployees.put(employee.getId(), employeeMapper.mapToDto(employee));
              employee.setDundieAwards(employee.getDundieAwards() + numberOfAwards);
            });

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
  public void update(Map<Long, EmployeeResponseDto> initialStateEmployees) {
    employeeService.bulkUpdateEmployees(initialStateEmployees);
    awardsCache.setTotalAwards(calculateTotalAwards());
  }
}
