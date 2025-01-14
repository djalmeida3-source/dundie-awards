package com.ninjaone.dundie_awards.controller.mapper;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.model.Employee;
import java.util.Collection;
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
                employeeRequestDto.getLastName(),
                employeeRequestDto.getDundieAwards()
                );
    }

    public Employee mapToEntity(EmployeeResponseDto responseDto) {
        return new Employee(
                responseDto.getId(),
                responseDto.getFirstName(),
                responseDto.getLastName(),
                responseDto.getDundieAwards(),
                organizationMapper.mapToEntity(responseDto.getOrganization())
        );
    }

    public List<Employee> mapToEntity(List<EmployeeResponseDto> responseDtos) {
        return responseDtos.stream().map(this::mapToEntity).collect(Collectors.toList());
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
