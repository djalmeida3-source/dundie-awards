package com.ninjaone.dundie_awards.model;

import com.ninjaone.dundie_awards.controller.dto.EmployeeRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "dundie_awards")
  private int dundieAwards;

  @ManyToOne
  private Organization organization;

  public Employee() {
  }

  public Employee(String firstName, String lastName, Organization organization) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.organization = organization;
  }

  public void updateFromDto(EmployeeRequestDto dto) {
    this.firstName = dto.getFirstName();
    this.lastName = dto.getLastName();
    this.dundieAwards = dto.getDundieAwards();
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

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }
}