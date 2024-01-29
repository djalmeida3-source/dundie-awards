package com.ninjaone.dundie_awards.messaging.event;

import com.ninjaone.dundie_awards.model.Employee;
import java.util.List;

public record NewActivityEvent(String nameActivity, List<Employee> employees) {}
