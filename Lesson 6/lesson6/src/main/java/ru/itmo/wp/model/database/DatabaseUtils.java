package ru.itmo.wp.model.database;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    public static DataSource getDataSource() {
        return DataSourceHolder.INSTANCE;
    }
//
//    public static <T> List<T> findAllByQuery(Wrapper<T> wrapper, String query, Object... queryArguments) {
//        List<T> elements = new ArrayList<>();
//        try (Connection connection = getDataSource().getConnection()) {
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                for (int i = 0; i < queryArguments.length; i++) {
//                    statement.setObject(i + 1, queryArguments[i]);
//                }
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    T element;
//                    while ((element = wrapper.wrap(statement.getMetaData(), resultSet)) != null) {
//                        elements.add(element);
//                    }
//                    return elements;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RepositoryException("Can't find elements all elements by query " + query, e);
//        }
//    }

    private static final class DataSourceHolder {
        private static final DataSource INSTANCE;
        private static final Properties properties = new Properties();

        static {
            try {
                properties.load(DataSourceHolder.class.getResourceAsStream("/application.properties"));
            } catch (IOException e) {
                throw new RuntimeException("Can't load /application.properties.", e);
            }

            try {
                MariaDbDataSource instance = new MariaDbDataSource();
                instance.setUrl(properties.getProperty("database.url"));
                instance.setUser(properties.getProperty("database.user"));
                instance.setPassword(properties.getProperty("database.password"));
                INSTANCE = instance;
            } catch (SQLException e) {
                throw new RuntimeException("Can't initialize DataSource.", e);
            }

            try (Connection connection = INSTANCE.getConnection()) {
                if (connection == null) {
                    throw new RuntimeException("Can't create testing connection via DataSource.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Can't create testing connection via DataSource.", e);
            }
        }
    }
}
