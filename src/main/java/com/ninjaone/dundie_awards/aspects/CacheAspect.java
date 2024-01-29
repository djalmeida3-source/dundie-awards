package com.ninjaone.dundie_awards.aspects;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.annotations.MyCache;
import com.ninjaone.dundie_awards.services.EmployeeService;
import java.util.Objects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
  @Autowired
  private AwardsCache awardsCache;
  @Autowired
  private EmployeeService employeeService;
  @AfterReturning("@annotation(myCache)")
  public void afterMethod(JoinPoint joinPoint, MyCache myCache) throws Throwable {
    updateTotalAwards();
  }

  private int calculateTotalAwards() {
    return employeeService.getAllEmployees().stream()
            .mapToInt(employee -> Objects.requireNonNullElse(employee.getDundieAwards(), 0))
            .sum();
  }

  private void updateTotalAwards() {
    awardsCache.setTotalAwards(calculateTotalAwards());
  }
}
