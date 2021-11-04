package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;

public class EventService {
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public Event addEnterEvent(User user) {
        return addEvent(user, Event.EventType.ENTER);
    }

    public Event addLogoutEvent(User user) {
        return addEvent(user, Event.EventType.LOGOUT);
    }

    public Event addEvent(User user, Event.EventType eventType) {
        if (user != null && eventType != null) {
            Event event = new Event();
            event.setUserId(user.getId());
            event.setType(eventType);
            return eventRepository.save(event);
        } else {
            throw new IllegalStateException("Event type and user must be not null");
        }
    }
}
