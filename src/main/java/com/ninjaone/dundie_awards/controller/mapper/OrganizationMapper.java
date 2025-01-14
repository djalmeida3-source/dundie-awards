package com.ninjaone.dundie_awards.controller.mapper;

import com.ninjaone.dundie_awards.controller.dto.OrganizationResponseDto;
import com.ninjaone.dundie_awards.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
  public OrganizationResponseDto mapToDto(Organization organization) {
    final OrganizationResponseDto organizationResponseDto = new OrganizationResponseDto();
    organizationResponseDto.setId(organization.getId());
    organizationResponseDto.setName(organization.getName());
    return organizationResponseDto;
  }

  public Organization mapToEntity(OrganizationResponseDto organizationResponseDto) {
    final Organization organization = new Organization();
    organization.setId(organizationResponseDto.getId());
    organization.setName(organizationResponseDto.getName());
    return organization;
  }
}
