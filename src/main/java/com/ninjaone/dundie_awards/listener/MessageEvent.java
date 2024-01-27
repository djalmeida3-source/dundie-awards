package com.ninjaone.dundie_awards.listener;

import com.ninjaone.dundie_awards.model.Activity;
import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

  private Activity message;

  public MessageEvent(Object source, Activity message) {
    super(source);
    this.message = message;
  }

  public Activity getMessage() {
    return message;
  }

  public void setMessage(Activity message) {
    this.message = message;
  }
}
