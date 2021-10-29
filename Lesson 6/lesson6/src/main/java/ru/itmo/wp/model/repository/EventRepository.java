package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Event;

public interface EventRepository extends AbstractRepository<Event> {
    Event findById(long id);
}
