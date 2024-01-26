package com.ninjaone.dundie_awards.listener;

import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.services.AwardsService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CommitEventListener implements ApplicationListener<CommitEvent> {
  @Autowired
  private MessageBroker messageBroker;

  @Autowired
  private AwardsService awardsService;

  @Override
  public void onApplicationEvent(CommitEvent event) {
    Activity message = new Activity(LocalDateTime.now(), event.getMessage());
    messageBroker.sendMessage(message);
    awardsService.updateTotalAwards();
  }

}
