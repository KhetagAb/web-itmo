package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.wrapper.EventWrapper;
import ru.itmo.wp.model.repository.wrapper.Wrapper;

public class EventRepositoryImpl extends AbstractRepositoryImpl<Event> implements EventRepository {
    private final Wrapper<Event> eventWrapper = new EventWrapper();
    private final String DATA_BASE_NAME = "Event";

    @Override
    protected String getDataBaseName() {
        return DATA_BASE_NAME;
    }

    @Override
    protected Wrapper<Event> getElementWrapper() {
        return eventWrapper;
    }
}
