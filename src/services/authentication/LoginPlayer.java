package services.authentication;

import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPlayer {
    private final Connection dbConnection;
    private static final String USER_EXISTS_QUERY = "SELECT name from player where name=?";
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    /**
     * Constructor that establishes a connection to the database.
     */
    public LoginPlayer() {
        this.dbConnection = DatabaseConnection.getDbConnection();
        if (this.dbConnection == null) {
            System.exit(1);
        }
    }

    /**
     * Checks if the database is connected to run queries.
     *
     * @return A boolean indicating if the database is connected.
     */
    public boolean isDbConnected() {
        try {
            return !this.dbConnection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * This method checks if the user already exists.
     *
     * @param name The name of the user.
     * @return A boolean representing if a user already exists.
     */
    public boolean checkUserExists(String name) {
        boolean userExistFlag = false;

        if (this.isDbConnected()) {
            try {
                this.preparedStatement = this.dbConnection.prepareStatement(USER_EXISTS_QUERY);
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String playerName = resultSet.getString(1);
                    if (name.equalsIgnoreCase(playerName)) {
                        userExistFlag = true;
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userExistFlag;

    }
}



