package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.wrapper.TalkWrapper;
import ru.itmo.wp.model.repository.wrapper.Wrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TalkRepositoryImpl extends AbstractRepositoryImpl<Talk> implements TalkRepository {
    private final Wrapper<Talk> talkWrapper = new TalkWrapper();
    private final String DATA_BASE_NAME = "Talk";

    @Override
    protected String getDataBaseName() {
        return DATA_BASE_NAME;
    }

    @Override
    protected Wrapper<Talk> getElementWrapper() {
        return talkWrapper;
    }

    @Override
    public List<Talk> findAllRelatedByUserId(long id) {
        List<Talk> elements = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + DATA_BASE_NAME + " WHERE (sourceUserId=? OR targetUserId=?) ORDER BY creationTime")) {
                statement.setLong(1, id);
                statement.setLong(2, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    Talk element;
                    while ((element = getElementWrapper().wrap(statement.getMetaData(), resultSet)) != null) {
                        elements.add(element);
                    }
                    return elements;
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find element in Data Base " + getDataBaseName(), e);
        }
    }
}
