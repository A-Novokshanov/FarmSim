package services.create;

import models.SettingModel;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePlayer {
    private final Connection dbConnection;
    private static final String CREATE_PLAYER_QUERY = "INSERT INTO player(name) VALUES(?)";

    /**
     * Constructor that establishes a connection to the database.
     */
    public CreatePlayer() {
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
     * Sets the player details in the database.
     *
     * @param settingModel The model class that contains all player information.
     */
    public void setPlayerDetails(SettingModel settingModel) {
        if (this.isDbConnected()) {
            try {
                PreparedStatement preparedStatement = this.dbConnection.prepareStatement(CREATE_PLAYER_QUERY);
                preparedStatement.setString(1, settingModel.getPlayerName());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

