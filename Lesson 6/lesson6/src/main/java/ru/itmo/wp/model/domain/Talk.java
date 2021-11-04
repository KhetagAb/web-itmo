package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.repository.wrapper.WrapAble;

import java.util.Date;
import java.util.Map;

public class Talk implements WrapAble {
    private long id;
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
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

    @Override
    public Map<String, Object> unwrap() {
        return Map.of("sourceUserId", this.getSourceUserId(),
                "targetUserId", this.getTargetUserId(),
                "text", this.getText());
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
