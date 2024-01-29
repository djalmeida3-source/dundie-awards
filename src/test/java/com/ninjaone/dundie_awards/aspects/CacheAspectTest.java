package com.ninjaone.dundie_awards.aspects;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.services.EmployeeService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheAspectTest {

  @InjectMocks
  private CacheAspect cacheAspect;

  @Mock
  private AwardsCache awardsCache;
  @Mock
  private EmployeeService employeeService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testUpdateTotalAwards() throws Throwable {
    // Given
    int expectedTotalAwards = 5;
    when(employeeService.getAllEmployees()).thenReturn(List.of(new EmployeeResponseDto(1L, "John", "Doe", 5, null)));

    // When
    cacheAspect.afterMethod(null, null);

    // Then
    verify(awardsCache, times(1)).setTotalAwards(expectedTotalAwards);
  }
}
