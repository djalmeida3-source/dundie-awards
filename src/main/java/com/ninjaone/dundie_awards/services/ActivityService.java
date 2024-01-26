package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;
  @Autowired
  private MessageBroker messageBroker;
  public Map<String, Boolean> processActivities() {
    activityRepository.saveAll(messageBroker.getMessages());
    messageBroker.clearMessages();
    return Map.of("Process", true);
  }

  public Activity registerActivity(LocalDateTime localDateTime, String event) {
    return activityRepository.save(new Activity(localDateTime, event));
  }
}
