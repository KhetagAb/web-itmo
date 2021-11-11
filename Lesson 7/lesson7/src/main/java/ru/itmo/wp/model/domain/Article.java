package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.repository.wrapper.WrapAble;

import java.util.Date;
import java.util.Map;

public class Article implements WrapAble {
    private long id;
    private long userId;
    private String title;
    private String text;
    private boolean hidden;
    private Date creationTime;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Map<String, Object> unwrap() {
        return Map.of("userId", userId,
                "title", title,
                "text", text,
                "isHidden", hidden);
    }
}
