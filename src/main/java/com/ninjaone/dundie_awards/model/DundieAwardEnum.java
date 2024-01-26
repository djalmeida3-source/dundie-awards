package com.ninjaone.dundie_awards.model;

public enum DundieAwardEnum {
  EMPLOYEE_OF_THE_YEAR("Employee of the year"),
  BEST_SELLER("Best seller"),
  BETTER_DRESSED("Better dressed"),
  MORE_SPICY_PERSON("More spicy person");

  private final String name;

  DundieAwardEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
