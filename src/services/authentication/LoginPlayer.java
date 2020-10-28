package services.authentication;

import models.CropModel;
import models.PlayerModel;
import models.PlotModel;
import models.SeasonModel;
import models.SettingModel;
import models.StorageModel;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginPlayer {
    private static final String USER_EXISTS_QUERY = "SELECT name from player where name=?";
    private static final String GET_USER_ID_MONEY = "SELECT id, money, days from player where name=?";
    private static final String GET_USER_SETTINGS = "SELECT difficulty, season, "
            + "seed from setting where player = ?";
    private static final String GET_PLAYER_CROPS = "SELECT * FROM crop WHERE player = ?";
    private PreparedStatement preparedStatement;

    /**
     * Constructor that establishes a connection to the database.
     */
    public LoginPlayer() {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (dbConnection == null) {
            System.exit(1);
        }
    }

    /**
     * Checks if the database is connected to run queries.
     *
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
     * This method checks if the user already exists.
     *
     * @param name The name of the user.
     * @return A boolean representing if a user already exists.
     */
    public boolean checkUserExists(String name) {
        boolean userExistFlag = false;
        Connection dbConnection = DatabaseConnection.getDbConnection();
        ResultSet resultSet = null;
        if (this.isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(USER_EXISTS_QUERY);
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
            } finally {
                try {
                    preparedStatement.close();
                    dbConnection.close();
                    assert resultSet != null;
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
    public PlayerModel queryPlayerMoneyAndSettings(String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        PlayerModel playerModel = null;
        ResultSet resultSet = null;
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(GET_USER_ID_MONEY);
                preparedStatement.setString(1, playerName);
                resultSet = preparedStatement.executeQuery();

                int playerId = resultSet.getInt("id");
                int currentMoney = resultSet.getInt("money");
                int days = resultSet.getInt("days");
                preparedStatement = dbConnection.prepareStatement(GET_USER_SETTINGS);
                preparedStatement.setInt(1, playerId);
                resultSet = preparedStatement.executeQuery();

                SeasonModel seasonModel = new SeasonModel(0, resultSet.getString("season"),
                        null, queryPlayerInventory(playerId).getInventory());

                CropModel cropModel = new CropModel(resultSet.getString("seed"),
                        1, 100);
                String difficulty = resultSet.getString("difficulty");
                SettingModel settingModel = new SettingModel(seasonModel,
                        cropModel, difficulty, playerName);

                //TODO add storage to database first, and then get the value to replace the null.
                playerModel = new PlayerModel(currentMoney, settingModel, queryPlayerInventory(playerId));
                playerModel.setDays(days);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                    dbConnection.close();
                    assert resultSet != null;
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

        return playerModel;
    }

    public StorageModel queryPlayerInventory(int playerId) {

        Connection dbConnection = DatabaseConnection.getDbConnection();
        StorageModel storage = new StorageModel();
        ResultSet resultSet = null;
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(GET_PLAYER_CROPS);
                preparedStatement.setInt(1, playerId);
                resultSet = preparedStatement.executeQuery();

                ArrayList<CropModel> cropInventoryList = new ArrayList<>();
                while (resultSet.next()) {
                    String cropName = resultSet.getString("name");
                    int cropQuantity = resultSet.getInt("quantity");
                    double cropValue = resultSet.getDouble("value");

                    CropModel crop = new CropModel(cropName, cropQuantity, cropValue);
                    cropInventoryList.add(crop);
                }

                storage.setCropInventory(cropInventoryList);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return storage;

    }

}



