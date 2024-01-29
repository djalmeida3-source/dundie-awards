package com.ninjaone.dundie_awards.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.messaging.dto.ActivityDto;
import com.ninjaone.dundie_awards.messaging.event.RestoreAwardEvent;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActivityServiceTest {
  @InjectMocks
  private ActivityService activityService;

  @Mock
  private ActivityRepository activityRepository;

  @Mock
  private MessageBroker messageBroker;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testRegisterActivity() {
    // Given
    ActivityDto dto = new ActivityDto("Test Activity", new HashMap<>());
    Activity activity = new Activity();
    when(activityRepository.save(any())).thenReturn(activity);

    // When
    Activity registeredActivity = activityService.registerActivity(dto);

    // Then
    assertEquals(registeredActivity, activity);
    verify(messageBroker, times(0)).publishEvent(any(RestoreAwardEvent.class));
  }

  @Test
  public void testRegisterActivityThrowsException() {
    // Given
    ActivityDto dto = new ActivityDto("Test Activity", new HashMap<>());
    when(activityRepository.save(any())).thenThrow(new RuntimeException());

    // When & Then
    assertThrows(RuntimeException.class, () -> activityService.registerActivity(dto));
    verify(messageBroker, times(1)).publishEvent(any(RestoreAwardEvent.class));
  }
}
