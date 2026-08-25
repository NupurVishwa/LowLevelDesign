package entities;

import java.time.LocalDateTime;

public class Content {
    private final String id ;
    private final String body;
    public final User author;
    private LocalDateTime creationtime;

    public Content(String id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.creationtime = creationtime;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationtime() {
        return creationtime;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }
}