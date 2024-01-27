package com.ninjaone.dundie_awards.services;

import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;


  public Activity registerActivity(Activity activity) {
    return activityRepository.save(null);
  }
}
