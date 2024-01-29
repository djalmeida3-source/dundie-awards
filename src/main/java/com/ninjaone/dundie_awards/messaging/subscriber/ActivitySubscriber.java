package com.ninjaone.dundie_awards.messaging.subscriber;

import com.ninjaone.dundie_awards.messaging.dto.ActivityDto;
import com.ninjaone.dundie_awards.messaging.event.NewActivityEvent;
import com.ninjaone.dundie_awards.services.ActivityService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ActivitySubscriber {
    private final ActivityService activityService;

    public ActivitySubscriber(ActivityService activityService) {
        this.activityService = activityService;
    }

    @EventListener
    public void handleNewActivityEvent(NewActivityEvent newActivityEvent) throws InterruptedException {
        Thread.sleep(10000);
        activityService.registerActivity(
                new ActivityDto(
                        newActivityEvent.nameActivity(),
                        newActivityEvent.initialStateEmployees()
                )
        );
    }
}
