package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.annotations.MyCache;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.messaging.event.NewActivityEvent;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AwardsService {
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private MessageBroker messageBroker;
  @Autowired
  private EmployeeMapper employeeMapper;

  @Transactional
  @MyCache
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

    return employeeMapper.mapToDto(updatedEmployees);
  }

}
