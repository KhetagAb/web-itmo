package ru.itmo.wp.model.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@FunctionalInterface
public interface Wrapper<T> {
    T wrap(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException;
}
