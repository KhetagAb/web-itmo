package ru.itmo.wp.lesson8.domain.model;

import javax.persistence.Lob;

public class Notice {
    private long id;
    @Lob
    private String content;
}
