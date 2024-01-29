package com.ninjaone.dundie_awards.controller.mapper;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.model.Employee;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    @Autowired
    private OrganizationMapper organizationMapper;

    public Employee mapToEntity(EmployeeRequestDto employeeRequestDto) {
        return new Employee(
                employeeRequestDto.getFirstName(),
                employeeRequestDto.getLastName()
                );
    }

    public EmployeeResponseDto mapToDto(Employee employee) {
        final EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setFirstName(employee.getFirstName());
        employeeResponseDto.setLastName(employee.getLastName());
        employeeResponseDto.setDundieAwards(employee.getDundieAwards());
        employeeResponseDto.setOrganization(organizationMapper.mapToDto(employee.getOrganization()));
        return employeeResponseDto;
    }

    public List<EmployeeResponseDto> mapToDto(List<Employee> employees) {
        return employees.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
