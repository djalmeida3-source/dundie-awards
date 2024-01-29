package com.ninjaone.dundie_awards.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public void testCalculateTotalAwards() {
    // Given
    EmployeeResponseDto employee1 = new EmployeeResponseDto(1L, "John", "Doe", 5, null);
    EmployeeResponseDto employee2 = new EmployeeResponseDto(2L, "Jane", "Doe", 10, null);
    List<EmployeeResponseDto> employees = Arrays.asList(employee1, employee2);
    when(employeeService.getAllEmployees()).thenReturn(employees);

    // When
    int totalAwards = awardsService.calculateTotalAwards();

    // Then
    assertEquals(totalAwards, 15);
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

  @Test
  public void testUpdate() {
    // Given
    Map<Long, EmployeeResponseDto> initialStateEmployees = new HashMap<>();
    initialStateEmployees.put(1L, new EmployeeResponseDto());

    // When
    awardsService.update(initialStateEmployees);

    // Then
    verify(employeeService, times(1)).bulkUpdateEmployees(initialStateEmployees);
  }

  @Test
  public void testUpdateTotalAwards() {
    // Given
    int expectedTotalAwards = 5;
    when(employeeService.getAllEmployees()).thenReturn(List.of(new EmployeeResponseDto(1L, "John", "Doe", 5, null)));

    // When
    awardsService.updateTotalAwards();

    // Then
    verify(awardsCache, times(1)).setTotalAwards(expectedTotalAwards);
  }
}
