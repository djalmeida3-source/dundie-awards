package com.ninjaone.dundie_awards.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.dto.OrganizationResponseDto;
import com.ninjaone.dundie_awards.controller.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private OrganizationRepository organizationRepository;

  @Mock
  private EmployeeMapper employeeMapper;

  @InjectMocks
  private EmployeeService employeeService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllEmployees() {
    Employee employee = new Employee();
    employee.setOrganization(new Organization());
    when(employeeRepository.findAll()).thenReturn(List.of(employee));

    List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
    assertEquals(1, employees.size());
  }

  @Test
  public void testCreateEmployeeWhenOrganizationNotFound() {
    EmployeeRequestDto dto = new EmployeeRequestDto();
    dto.setOrganizationId(1L);
    when(organizationRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> employeeService.createEmployee(dto));
  }

  @Test
  public void testGetEmployeeById() {
    Employee employee = new Employee();
    employee.setId(1L);
    employee.setOrganization(new Organization());

    EmployeeResponseDto expectedResponseDto = new EmployeeResponseDto();
    expectedResponseDto.setId(employee.getId());
    expectedResponseDto.setOrganization(new OrganizationResponseDto());

    when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
    when(employeeMapper.mapToDto((Employee) any())).thenReturn(expectedResponseDto);

    EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeById(1L);

    assertEquals(expectedResponseDto, employeeResponseDto);
  }

  @Test
  public void testUpdateEmployeeChangeFirstName() {
    EmployeeRequestDto dto = new EmployeeRequestDto();
    dto.setFirstName("John");
    dto.setOrganizationId(1L);

    Employee employee = new Employee();
    employee.setId(1L);
    employee.setOrganization(new Organization());
    employee.setFirstName("Eric");

    EmployeeResponseDto dtoResponse = new EmployeeResponseDto();
    dtoResponse.setId(1L);
    dtoResponse.setFirstName("John");

    when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
    when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
    when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(new Organization()));
    when(employeeMapper.mapToDto((Employee) any())).thenReturn(dtoResponse);

    EmployeeResponseDto employeeResponseDto = employeeService.updateEmployee(1L, dto);
    assertNotNull(employeeResponseDto);
    assertEquals("John", employeeResponseDto.getFirstName());
  }

  @Test
  public void testDeleteEmployee() {
    when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));

    Boolean result = employeeService.deleteEmployee(1L);
    assertTrue(result);
  }
}
