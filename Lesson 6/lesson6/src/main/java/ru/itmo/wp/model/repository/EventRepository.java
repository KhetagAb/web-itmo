package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface EventRepository {
    Event find(long id);
    int findCount();
    List<Event> findAll();
    void save(Event event);
}
