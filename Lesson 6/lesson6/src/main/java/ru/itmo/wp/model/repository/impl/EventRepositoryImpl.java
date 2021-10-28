package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.Wrapper;
import ru.itmo.wp.model.repository.wrapper.EventWrapper;

import java.util.List;

public class EventRepositoryImpl extends AbstractRepositoryImpl implements EventRepository {
    private final Wrapper<Event> userWrapper = new EventWrapper();
    private final String DATA_BASE_NAME = "Event";

    @Override
    protected String getDataBaseName() {
        return null;
    }

    @Override
    protected Wrapper<?> getElementWrapper() {
        return null;
    }

    @Override
    public Event find(long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void save(Event event) {

    }

    public void save(User user, String passwordSha) {

    }
}
