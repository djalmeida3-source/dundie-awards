package com.ninjaone.dundie_awards.controller.dto;

import com.ninjaone.dundie_awards.model.Employee;

public class EmployeeResponseDto {
  private long id;
  private String firstName;
  private String lastName;
  private Integer dundieAwards;
  private OrganizationResponseDto organization;

  public EmployeeResponseDto() {
  }

  public EmployeeResponseDto(Employee employee) {
    this.id = employee.getId();
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
    this.dundieAwards = employee.getDundieAwards();
    this.organization = new OrganizationResponseDto(employee.getOrganization());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getDundieAwards() {
    return dundieAwards;
  }

  public void setDundieAwards(Integer dundieAwards) {
    this.dundieAwards = dundieAwards;
  }

  public OrganizationResponseDto getOrganization() {
    return organization;
  }

  public void setOrganization(OrganizationResponseDto organization) {
    this.organization = organization;
  }
}
