package com.ninjaone.dundie_awards.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AwardServiceTest {
  @InjectMocks
  private AwardsService awardsService;

  @Mock
  private AwardsCache awardsCache;

  @Mock
  private EmployeeService employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private MessageBroker messageBroker;

  @Mock
  private EmployeeMapper employeeMapper;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGrantAwardByOrganization() {
    // Given
    Long organizationId = 1L;
    int numberOfAwards = 5;
    Employee employee = new Employee(1L, "John", "Doe", 5, null);
    List<Employee> employees = Collections.singletonList(employee);
    when(employeeRepository.findByOrganizationId(organizationId)).thenReturn(employees);
    when(employeeRepository.saveAll(employees)).thenReturn(employees);
    when(employeeMapper.mapToDto(employees)).thenReturn(List.of(new EmployeeResponseDto(1L, "John", "Doe", 5, null)));

    // When
    List<EmployeeResponseDto> updatedEmployees = awardsService.grantAwardByOrganization(organizationId, numberOfAwards);

    // Then
    assertEquals(updatedEmployees.size(), 1);
  }

}
