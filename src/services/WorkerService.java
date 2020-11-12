package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles database operations for the worker table in the database.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class WorkerService {

    private static final String ADD_WORKER = "INSERT INTO worker(type, wage) VALUES (?, ?)";
    private static final String UPDATE_WORKER_WAGE = "UPDATE worker SET wage = ? ";
    private static final String UPDATE_WORKER_TYPE = "UPDATE worker SET type = ?";

    private PreparedStatement preparedStatement;

    /**
     *
     */
    public WorkerService() {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (dbConnection == null) {
            System.exit(1);
        }
    }

    /**
     * Checks if the database is connected to run queries.
     *
     * @param dbConnection The connection instance to check if connected.
     * @return A boolean indicating if the database is connected.
     */
    public boolean isDbConnected(Connection dbConnection) {
        try {
            return !dbConnection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Add worker to database.
     *
     * @param workerType The type of worker.
     * @param workerWage The wage of the worker.
     */
    public void addWorker(int workerType, int workerWage) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        try {
            preparedStatement = dbConnection.prepareStatement(ADD_WORKER);
            preparedStatement.setInt(1, workerType);
            preparedStatement.setInt(2, workerWage);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                dbConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Adjusts the worker's wage in the database.
     *
     * @param workerWage The wage to update by.
     */
    public void adjustWorkerWage(int workerWage) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        try {
            preparedStatement = dbConnection.prepareStatement(UPDATE_WORKER_WAGE);
            preparedStatement.setInt(1, workerWage);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                dbConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Adjusts the worker's type in the database.
     *
     * @param workerType The type of the worker to update by.
     */
    public void adjustWorkerType(int workerType) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        try {
            preparedStatement = dbConnection.prepareStatement(UPDATE_WORKER_TYPE);
            preparedStatement.setInt(1, workerType);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                dbConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
