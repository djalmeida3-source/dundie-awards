package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.AwardsCache;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwardsService {

  @Autowired
  private AwardsCache awardsCache;
  @Autowired
  private EmployeeService employeeService;
  public int calculateTotalAwards() {
    return employeeService.getAllEmployees().stream()
      .mapToInt(employee -> Objects.requireNonNullElse(employee.getDundieAwards(), 0))
      .sum();
  }

  public void updateTotalAwards() {
    awardsCache.setTotalAwards(calculateTotalAwards());
  }
}
