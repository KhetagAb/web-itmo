package ru.itmo.wp.model.dto;

import java.util.Date;

public class TalkDto {
    private final long id;
    private final String sourceUserLogin;
    private final String targetUserLogin;
    private final String text;
    private final Date creationTime;

    public TalkDto(long id, String sourceUser, String targetUser, String text, Date creationTime) {
        this.id = id;
        this.sourceUserLogin = sourceUser;
        this.targetUserLogin = targetUser;
        this.text = text;
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public String getSourceUserLogin() {
        return sourceUserLogin;
    }

    public String getTargetUserLogin() {
        return targetUserLogin;
    }

    public String getText() {
        return text;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
