package ru.itmo.web.lesson4.model;

public class Post {
    private final Long id;
    private final String title;
    private final String text;
    private final Long userId;

    public Post(Long id, String title, String text, Long userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }
}
