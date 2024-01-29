package com.ninjaone.dundie_awards.messaging.dto;

import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import java.util.Map;

public record ActivityDto(String nameActivity, Map<Long, EmployeeResponseDto> initialStateEmployees) {

}
