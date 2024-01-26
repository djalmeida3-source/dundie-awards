package com.ninjaone.dundie_awards.model;

public enum DundieAwardEnum {
  EMPLOYEE_OF_THE_YEAR(1, "Employee of the year"),
  BEST_SELLER(2, "Best seller"),
  BETTER_DRESSED(3, "Better dressed"),
  MORE_SPICY_PERSON(4, "More spicy person");

  private final int id;
  private final String name;

  DundieAwardEnum(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static DundieAwardEnum getById(int id) {
    for (DundieAwardEnum award : values()) {
      if (award.getId() == id) {
        return award;
      }
    }
    throw new IllegalArgumentException("No Dundie Award with id " + id);
  }
}
