package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.messaging.dto.ActivityDto;
import com.ninjaone.dundie_awards.messaging.MessageBroker;
import com.ninjaone.dundie_awards.messaging.event.RestoreAwardEvent;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;

  @Autowired
  private MessageBroker messageBroker;


  @Transactional
  public Activity registerActivity(ActivityDto dto) {
    // Added null to test rollback on MessageBroker
    try {
      return activityRepository.save(
              // new Activity(LocalDateTime.now(), dto.getNameActivity())
              null
      );
    } catch (Exception ex) {
      messageBroker.publishEvent(new RestoreAwardEvent(
              dto.employees()
      ));
      throw ex;
    }
  }
}
