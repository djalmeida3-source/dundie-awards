package com.ninjaone.dundie_awards.messaging;

import com.ninjaone.dundie_awards.messaging.event.NewActivityEvent;
import com.ninjaone.dundie_awards.messaging.event.RestoreAwardEvent;
import com.ninjaone.dundie_awards.model.Activity;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.xml.crypto.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessageBroker {
    private final ApplicationEventPublisher publisher;
    private List<Activity> messages = new LinkedList<>();
    public MessageBroker(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public List<Activity> getMessages() {
        return messages;
    }

    @Async
    public void publishEvent(RestoreAwardEvent restoreAwardEvent) {
        publisher.publishEvent(restoreAwardEvent);
    }

    @Async
    public void publishEvent(NewActivityEvent newActivityEvent) {
        messages.add(new Activity(
            LocalDateTime.now(),
            newActivityEvent.nameActivity()
        ));
        publisher.publishEvent(newActivityEvent);
    }
}
