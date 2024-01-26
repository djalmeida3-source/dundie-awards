package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
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
}
