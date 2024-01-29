package com.ninjaone.dundie_awards.messaging.event;

import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import java.util.Map;

public record RestoreAwardEvent(Map<Long, EmployeeResponseDto> initialStateEmployees) {
}
