package com.ninjaone.dundie_awards.listener;

import java.time.Clock;
import org.springframework.context.ApplicationEvent;

public class CommitEvent extends ApplicationEvent {

  private String message;
  public CommitEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public CommitEvent(Object source, Clock clock) {
    super(source, clock);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
