package com.ninjaone.dundie_awards.messaging;

import com.ninjaone.dundie_awards.messaging.event.NewActivityEvent;
import com.ninjaone.dundie_awards.messaging.event.RestoreAwardEvent;
import com.ninjaone.dundie_awards.model.Activity;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
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
        registerQueueMessage(LocalDateTime.now(), "Restore grant award by organization event");
        publisher.publishEvent(restoreAwardEvent);
    }

    @Async
    public void publishEvent(NewActivityEvent newActivityEvent) {
        registerQueueMessage(LocalDateTime.now(), newActivityEvent.nameActivity());
        publisher.publishEvent(newActivityEvent);
    }
    private void registerQueueMessage(LocalDateTime localDateTime, String message) {
        messages.add(new Activity(
                localDateTime,
                message));
    }
}
