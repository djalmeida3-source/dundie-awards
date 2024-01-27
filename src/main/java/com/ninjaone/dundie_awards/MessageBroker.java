package com.ninjaone.dundie_awards;

import com.ninjaone.dundie_awards.listener.MessageEvent;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.services.ActivityService;
import com.ninjaone.dundie_awards.services.AwardsService;
import com.ninjaone.dundie_awards.services.EmployeeService;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MessageBroker {

    private final ApplicationEventPublisher publisher;
    private List<Activity> messages = new LinkedList<>();
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AwardsCache awardsCache;

    @Autowired
    public MessageBroker(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendMessage(Activity message, List<Employee> initialStateEmployees) {
        publisher.publishEvent(new MessageEvent(this, message, initialStateEmployees));
    }

    public List<Activity> getMessages() {
        return messages;
    }

    @EventListener
    public void handleNewMessage(MessageEvent event) {
        try {
            // Wait for 10 seconds
            Thread.sleep(10000);
            Activity message = event.getMessage();
            activityService.registerActivity(message);
            messages.add(message);
        } catch (Exception e) {
            // Rollback to initial state
            rollBackGrantAward(event);
        }
    }

    private void rollBackGrantAward(MessageEvent event) {
        employeeService.updateEmployees(event.getInitialStateEmployees());
        awardsCache.setTotalAwards(employeeService.getAllEmployees().stream()
                .mapToInt(employee -> Objects.requireNonNullElse(employee.getDundieAwards(), 0))
                .sum());
    }
}
