package entities;

import Enum.EventType;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

    private final String id;

    // What happened?
    private final EventType event;

    // Who performed the action?
    private final User actor;

    // On which post?
    private final Post targetPost;

    // When did it happen?
    private final LocalDateTime timestamp;

    public Event(EventType event, User actor, Post targetPost) {

        this.id = UUID.randomUUID().toString();
        this.event = event;
        this.actor = actor;
        this.targetPost = targetPost;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public EventType getEvent() {
        return event;
    }

    public User getActor() {
        return actor;
    }

    public Post getTargetPost() {
        return targetPost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}