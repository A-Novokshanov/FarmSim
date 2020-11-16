package services.player;

import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class PlayerSettingsService {

    private static final String PLAYER_UPDATE_MONEY_QUERY =
            "UPDATE player SET money = money + ? WHERE name = ?";
    private static final String UPDATE_PLAYER_DAY =
            "UPDATE player SET days = days + ? WHERE name = ?";
    private static final String UPDATE_PLAYER_HARVEST = "UPDATE player SET harvest = ? WHERE name = ? ";
    private static final String UPDATE_PLAYER_WATER = "UPDATE player SET water = ? WHERE name = ?";
    private static final String UPDATE_PLAYER_MAX_HARVEST = "UPDATE player SET maxharvest = ? WHERE name = ?";
    private static final String UPDATE_PLAYER_MAX_WATER = "UPDATE player SET maxwater = ? WHERE name = ?";
    private PreparedStatement preparedStatement;

    /**
     * Establish connection to the database.
     */
    public PlayerSettingsService() {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        dbConnection = DatabaseConnection.getDbConnection();
        if (dbConnection == null) {
            System.exit(1);
        }
    }

    /**
     * Checks if the database is connected to run queries.
     *
     * @param dbConnection The connection to check.
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
     * This method updates a users money based on a transaction.
     *
     * @param money      The amount of money to add or subtract from user total.
     * @param playerName The name of the player whose money needs to be updated.
     */
    public void updatePlayerMoney(double money, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(PLAYER_UPDATE_MONEY_QUERY);
                preparedStatement.setDouble(1, money);
                preparedStatement.setString(2, playerName);
                preparedStatement.executeUpdate();

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

    /**
     * This method updates the players day in the homescreen.
     *
     * @param day        The current day.
     * @param playerName The name of the player whose day needs to be changed.
     */
    public void updatePlayerDay(int day, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_PLAYER_DAY);
                preparedStatement.setInt(1, day);
                preparedStatement.setString(2, playerName);
                preparedStatement.executeUpdate();

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

    /**
     * Updates the player's harvest counter in the database.
     *
     * @param harvest    The updated harvest counter.
     * @param playerName The name of the current player.
     */
    public void updatePlayerHarvest(int harvest, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_PLAYER_HARVEST);
                preparedStatement.setInt(1, harvest);
                preparedStatement.setString(2, playerName);
                preparedStatement.executeUpdate();

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

    /**
     * Updates the player's water counter in the database.
     *
     * @param water      The updated water counter.
     * @param playerName The name of the current player.
     */
    public void updatePlayerWater(int water, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_PLAYER_WATER);
                preparedStatement.setInt(1, water);
                preparedStatement.setString(2, playerName);
                preparedStatement.executeUpdate();

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

    /**
     * Updates the max harvest of the player in the database.
     *
     * @param maxHarvest The updated max harvest amount.
     * @param playerName The name of the player.
     */
    public void updatePlayerMaxHarvest(int maxHarvest, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_PLAYER_MAX_HARVEST);
                preparedStatement.setInt(1, maxHarvest);
                preparedStatement.setString(2, playerName);
                preparedStatement.executeUpdate();

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

    /**
     * Updates the max water of a am player in the database.
     *
     * @param maxWater   The updated max water amount.
     * @param playerName The name of the player.
     */
    public void updatePlayerMaxWater(int maxWater, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_PLAYER_MAX_WATER);
                preparedStatement.setInt(1, maxWater);
                preparedStatement.setString(2, playerName);
                preparedStatement.executeUpdate();

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
}
