package services.player;

import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerSettingsService {

    //Global variable Connection
    private final Connection dbConnection;
    private static final String PLAYER_UPDATE_MONEY_QUERY =
            "UPDATE player SET money = money + ? WHERE name = ?";
    private PreparedStatement preparedStatement;

    /**
     * Establish connection to the database.
     */
    public PlayerSettingsService() {
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
     * This method updates a users money based on a transaction.
     *
     * @param money      The amount of money to add or subtract from user total.
     * @param playerName The name of the player whose money needs to be updated.
     */
    public void updatePlayerMoney(double money, String playerName) {
        if (isDbConnected()) {
            try {
                preparedStatement = this.dbConnection.prepareStatement(PLAYER_UPDATE_MONEY_QUERY);
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
}
