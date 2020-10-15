package services.authentication;

import models.PlayerModel;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatePlayer {
    private final Connection dbConnection;
    private static final String CREATE_PLAYER_QUERY =
            "INSERT INTO player(name, money) VALUES(?, ?)";
    private static final String GET_PLAYER_ID =
            "SELECT a.id FROM player a WHERE a.name = ";
    private static final String CREATE_PLAYER_SETTINGS =
            "INSERT INTO setting(difficulty, season, seed, player) "
                    + "VALUES(?, ?, ?, ?)";
    private ResultSet resultSet;

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
     * @param playerDetails The model class that contains all player information.
     */
    public void setPlayerDetails(PlayerModel playerDetails) {
        if (this.isDbConnected()) {
            try {
                PreparedStatement preparedStatement = this.
                        dbConnection.prepareStatement(CREATE_PLAYER_QUERY);
                preparedStatement.setString(1,
                        playerDetails.getPlayerSettings().getPlayerName());
                preparedStatement.setDouble(2,
                        playerDetails.getUserCurrentMoney());
                preparedStatement.executeUpdate();

                String query = GET_PLAYER_ID + "\'"
                        + playerDetails.getPlayerSettings().getPlayerName() + "\'";
                preparedStatement = this.dbConnection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();

                int playerId = resultSet.getInt("id");

                preparedStatement = this.dbConnection.prepareStatement(CREATE_PLAYER_SETTINGS);
                preparedStatement.setString(1,
                        playerDetails.getPlayerSettings().getStartingDifficulty());
                preparedStatement.setString(2,
                        playerDetails.getPlayerSettings().getStartingSeason().getSeasonType());
                preparedStatement.setString(3,
                        playerDetails.getPlayerSettings().getStartingCropType().getCropName());
                preparedStatement.setInt(4, playerId);
                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}

