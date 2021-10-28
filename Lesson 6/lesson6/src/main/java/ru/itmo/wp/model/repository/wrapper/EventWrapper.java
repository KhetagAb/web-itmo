package ru.itmo.wp.model.repository.wrapper;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.Wrapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class EventWrapper implements Wrapper<Event> {
    @Override
    public Event wrap(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Event event = new Event();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    event.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    event.setUserId(Long.parseLong(resultSet.getString(i)));
                    break;
                case "":
                    event.setType(Event.EventType.valueOf(resultSet.getString(i)));
                    break;
                case "creationTime":
                    event.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return event;
    }
}
