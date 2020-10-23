package services.authentication;

import models.CropModel;
import models.PlayerModel;
import models.SeasonModel;
import models.SettingModel;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPlayer {
    private final Connection dbConnection;
    private static final String USER_EXISTS_QUERY = "SELECT name from player where name=?";
    private static final String GET_USER_ID_MONEY = "SELECT id, money from player where name=?";
    private static final String GET_USER_SETTINGS = "SELECT difficulty, season, "
            + "crop from setting where player = ?";
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    /**
     * Constructor that establishes a connection to the database.
     */
    public LoginPlayer() {
        dbConnection = DatabaseConnection.getDbConnection();
        if (dbConnection == null) {
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
                preparedStatement = this.dbConnection.prepareStatement(USER_EXISTS_QUERY);
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

    /**
     * Gets user details from the database and populates the models.
     *
     * @param playerName The name of the player to get the settings of.
     * @return The player model class that holds player details.
     */
    public PlayerModel getPlayerDetails(String playerName) {
        if (isDbConnected()) {
            try {
                preparedStatement = dbConnection.prepareStatement(GET_USER_ID_MONEY);
                preparedStatement.setString(1, playerName);
                resultSet = preparedStatement.executeQuery();

                int playerId = resultSet.getInt("id");
                int currentMoney = resultSet.getInt("money");
                preparedStatement = dbConnection.prepareStatement(GET_USER_SETTINGS);
                preparedStatement.setInt(1, playerId);
                resultSet = preparedStatement.executeQuery();

                SeasonModel seasonModel = new SeasonModel(0, resultSet.getString("season"),
                        null, null);

                CropModel cropModel = new CropModel(resultSet.getString("crop"),
                        resultSet.getInt("quantity"), resultSet.getDouble("value"));
                String difficulty = resultSet.getString("difficulty");
                SettingModel settingModel = new SettingModel(seasonModel,
                        cropModel, difficulty, playerName);

                //TODO add storage to database first, and then get the value to replace the null.
                return new PlayerModel(currentMoney, settingModel, null);


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

        return null;
    }
}



