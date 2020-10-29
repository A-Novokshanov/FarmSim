package services.player;

import models.CropModel;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class PlayerInventoryService {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final String GET_USER_ID_QUERY =
            "SELECT a.id FROM player a WHERE a.name = ?";
    private static final String ADD_CROPS_QUERY =
            "INSERT INTO crop(name, value, quantity, player) VALUES(?, ?, ? ,?)";
    private static final String UPDATE_QUANTITY_QUERY =
            "UPDATE crop SET quantity = quantity + ? "
            + "WHERE name = ? AND player = ?";

    /**
     * Establish connection to the database.
     */
    public PlayerInventoryService() {
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
     * This method gets the id of the player from the database.
     *
     * @param playerName The name of the player whose id to get.
     * @return The player id.
     */
    public int getPlayerId(String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {

                preparedStatement = dbConnection.prepareStatement(GET_USER_ID_QUERY);
                preparedStatement.setString(1, playerName);
                resultSet = preparedStatement.executeQuery();

                return resultSet.getInt("id");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    dbConnection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * This method adds the players initial crops to the database.
     *
     * @param playerName  The name of the player whose crops need to be added.
     * @param listOfCrops All the crops that need to be added to the database.
     */
    public void addPlayerCrops(String playerName, List<CropModel> listOfCrops) {
        int playerId = this.getPlayerId(playerName);
        Connection dbConnection = DatabaseConnection.getDbConnection();
        try {
            preparedStatement = dbConnection.prepareStatement(ADD_CROPS_QUERY);
            for (CropModel listOfCrop : listOfCrops) {
                preparedStatement.setString(1, listOfCrop.getCropName());
                preparedStatement.setDouble(2, listOfCrop.getCropValue());
                preparedStatement.setInt(3, listOfCrop.getCropQuantity());
                preparedStatement.setInt(4, playerId);
                preparedStatement.execute();
            }

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
     * This method adjusts the quantity of the crops in storage.
     *
     * @param cropName   The crop whose quantity needs to be adjusted.
     * @param quantity   The amount of the crop purchased.
     * @param playerName The name of the player whose storage needs to be adjusted.
     */
    public void adjustCropQuantity(String cropName, int quantity, String playerName) {
        int playerId = this.getPlayerId(playerName);
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_QUANTITY_QUERY);
                preparedStatement.setInt(1, quantity);
                preparedStatement.setString(2, cropName);
                preparedStatement.setInt(3, playerId);
                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    dbConnection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }

}
