package com.ninjaone.dundie_awards.listener;

import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.model.Employee;
import java.util.List;
import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

  private Activity message;

  private List<Employee> initialStateEmployees;

  public MessageEvent(Object source, Activity message, List<Employee> initialStateEmployees) {
    super(source);
    this.message = message;
    this.initialStateEmployees = initialStateEmployees;
  }

  public Activity getMessage() {
    return message;
  }

  public void setMessage(Activity message) {
    this.message = message;
  }

  public List<Employee> getInitialStateEmployees() {
    return initialStateEmployees;
  }

  public void setInitialStateEmployees(List<Employee> initialStateEmployees) {
    this.initialStateEmployees = initialStateEmployees;
  }
}
