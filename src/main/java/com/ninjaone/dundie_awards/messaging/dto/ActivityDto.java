package com.ninjaone.dundie_awards.messaging.dto;

import com.ninjaone.dundie_awards.model.Employee;
import java.util.List;

public record ActivityDto(String nameActivity, List<Employee> employees) {

}
