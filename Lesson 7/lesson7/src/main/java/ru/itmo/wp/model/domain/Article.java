package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private long id;
    private long userId;
    private String title;
    private String text;
    private Date creationTime;
}
