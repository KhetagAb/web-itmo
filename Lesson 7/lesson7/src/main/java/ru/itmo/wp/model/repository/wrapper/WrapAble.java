package ru.itmo.wp.model.repository.wrapper;

import java.util.Date;
import java.util.Map;

public interface WrapAble {
    long getId();

    void setId(long id);

    Date getCreationTime();

    void setCreationTime(Date creationTime);

    Map<String, Object> unwrap();
}
