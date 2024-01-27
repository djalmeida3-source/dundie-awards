package com.ninjaone.dundie_awards.config;

import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
  @Autowired
  private MessageBroker messageBroker;
  @Async
  public void publishEvent(Activity message, List<Employee> initialStateEmployees) {
    messageBroker.sendMessage(message, initialStateEmployees);
  }
}
