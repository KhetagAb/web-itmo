package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.wrapper.Wrapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRepositoryImpl<T> {
    protected final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    protected abstract String getDataBaseName();

    protected abstract Wrapper<T> getElementWrapper();

    public T findById(long id) {
        return findByAllArguments(new String[]{"id"}, new Object[]{id});
    }

    public List<T> findAll() {
        List<T> elements = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + getDataBaseName())) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    T element;
                    while ((element = getElementWrapper().wrap(statement.getMetaData(), resultSet)) != null) {
                        elements.add(element);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find element in Data Base " + getDataBaseName(), e);
        }
        return elements;
    }

    public int findCount() {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + getDataBaseName())) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't count elements in Data Base " + getDataBaseName(), e);
        }
    }

    protected T findByAllArguments(String[] argumentNames, Object[] argumentValues) {
        String query = Arrays.stream(argumentNames)
                .map(e -> e + "=?")
                .collect(Collectors.joining(" AND "));

        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + getDataBaseName() + " WHERE " + query)) {
                for (int i = 0; i < argumentNames.length; i++) {
                    statement.setObject(i + 1, argumentValues[i]);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    return getElementWrapper().wrap(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find element in Data Base " + getDataBaseName(), e);
        }
    }
}
