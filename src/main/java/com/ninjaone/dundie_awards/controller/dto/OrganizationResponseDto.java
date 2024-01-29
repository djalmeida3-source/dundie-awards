package com.ninjaone.dundie_awards.controller.dto;

import com.ninjaone.dundie_awards.model.Organization;

public class OrganizationResponseDto {
  private long id;
  private String name;

  public OrganizationResponseDto() {
  }

  public OrganizationResponseDto(Organization organization) {
    this.id = organization.getId();
    this.name = organization.getName();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
