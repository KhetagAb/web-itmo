package ru.itmo.wp.model.repository.wrapper;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.Wrapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class EventWrapper implements Wrapper<Event> {
    @Override
    public Event wrap(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Object[] unwrap(Event element) {
        return new Object[0];
    }
}
