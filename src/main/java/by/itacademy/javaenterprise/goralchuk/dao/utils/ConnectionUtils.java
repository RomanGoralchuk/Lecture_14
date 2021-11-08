package by.itacademy.javaenterprise.goralchuk.dao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class ConnectionUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtils.class);

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }

    public static void closePrepareStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }

    public static void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                logger.info("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }

    public static void rollbackTransaction(Connection connection, Savepoint savepoint) {
        if (connection != null) {
            try {
                logger.info("Transaction is being rolled back to " + savepoint);
                connection.rollback(savepoint);
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }

}
