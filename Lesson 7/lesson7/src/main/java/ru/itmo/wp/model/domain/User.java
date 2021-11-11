package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.repository.wrapper.WrapAble;

import java.util.Date;
import java.util.Map;

public class User implements WrapAble {
    private long id;
    private String login;
    private boolean isAdmin;
    private Date creationTime;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Map<String, Object> unwrap() {
        return Map.of("login", login,
                "admin", isAdmin);
    }
}
