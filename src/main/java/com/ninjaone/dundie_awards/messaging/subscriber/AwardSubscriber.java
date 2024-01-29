package com.ninjaone.dundie_awards.messaging.subscriber;

import com.ninjaone.dundie_awards.messaging.dto.RestoreAwardRequestDto;
import com.ninjaone.dundie_awards.messaging.event.RestoreAwardEvent;
import com.ninjaone.dundie_awards.services.AwardsService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AwardSubscriber {

    private final AwardsService awardsService;

    public AwardSubscriber(AwardsService awardsService) {
        this.awardsService = awardsService;
    }

    @EventListener
    public void handleNewActivityEvent(RestoreAwardEvent restoreAwardEvent) {
        awardsService.restore(
                new RestoreAwardRequestDto(
                        restoreAwardEvent.employees()
                )
        );
    }
}
