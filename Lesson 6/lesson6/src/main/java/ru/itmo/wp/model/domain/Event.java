package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.repository.wrapper.WrapAble;

import java.util.Date;
import java.util.Map;

public class Event implements WrapAble {
    private long id;
    private long userId;
    private EventType type;
    private Date creationTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public EventType getType() {
        return type;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public Map<String, Object> unwrap() {
        return Map.of("userId", this.getUserId(),
                "type", this.getType().getDBParameter());
    }

    public enum EventType {
        ENTER("ENTER"),
        LOGOUT("LOGOUT");

        private final String DB_PARAMETER;

        EventType(String db_name) {
            DB_PARAMETER = db_name;
        }

        public String getDBParameter() {
            return DB_PARAMETER;
        }
    }
}
