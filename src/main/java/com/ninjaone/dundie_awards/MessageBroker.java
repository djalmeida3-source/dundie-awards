package com.ninjaone.dundie_awards;

import com.ninjaone.dundie_awards.listener.MessageEvent;
import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.services.ActivityService;
import java.util.LinkedList;
import java.util.List;
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
    public MessageBroker(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendMessage(Activity message) {
        publisher.publishEvent(new MessageEvent(this, message));
    }

    public List<Activity> getMessages() {
        return messages;
    }

    @EventListener
    public void handleNewMessage(MessageEvent event) {
        Activity message = event.getMessage();
        activityService.registerActivity(message);
        messages.add(message);
    }
}
