package com.ninjaone.dundie_awards.config;

import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
  @Autowired
  private MessageBroker messageBroker;
  @Async
  public void publishEvent(Activity message) {
    messageBroker.sendMessage(message);
  }
}
