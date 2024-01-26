package com.ninjaone.dundie_awards.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDto {
  @NotNull
  @NotEmpty
  private String firstName;
  @NotNull
  @NotEmpty
  private String lastName;
  @NotNull
  private Long organizationId;
  private Integer dundieAwards;

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

  public long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(long organizationId) {
    this.organizationId = organizationId;
  }

  public Integer getDundieAwards() {
    return dundieAwards;
  }

  public void setDundieAwards(Integer dundieAwards) {
    this.dundieAwards = dundieAwards;
  }
}
