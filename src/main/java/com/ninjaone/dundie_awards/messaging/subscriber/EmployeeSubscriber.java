package com.ninjaone.dundie_awards.messaging.subscriber;

import com.ninjaone.dundie_awards.messaging.event.RestoreAwardEvent;
import com.ninjaone.dundie_awards.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSubscriber {

    @Autowired
    private EmployeeService employeeService;

    @EventListener
    public void handleEmployeeUpdateEvent(RestoreAwardEvent restoreAwardEvent) {
        employeeService.bulkUpdateEmployees(restoreAwardEvent.initialStateEmployees());
    }
}
