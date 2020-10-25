package services.player;

import models.PlotModel;
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
public class PlayerPlotService {
    private PreparedStatement preparedStatement;
    private static final String ADD_PLOTS_QUERY = "INSERT INTO plot(days, water, crop, player) VALUES(?, ?, ?, ?)";
    private static final String GET_USER_ID_QUERY = "SELECT a.id FROM player a WHERE a.name = ?";
    private static final String UPDATE_PLOT_MATURITY = "UPDATE plot SET days = days + 1, water = water + 1 "
            + "WHERE crop = ? AND player = ?";

    /**
     *
     */
    public PlayerPlotService() {
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
     * @param playerName
     * @return
     */
    private int getPlayerId(String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        if (isDbConnected(dbConnection)) {
            try {

                preparedStatement = dbConnection.prepareStatement(GET_USER_ID_QUERY);
                preparedStatement.setString(1, playerName);
                ResultSet resultSet = preparedStatement.executeQuery();

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
     * Adds the player plots to the database.
     *
     * @param playerPlots the list of player plots.
     * @param playerName  the name of the player.
     */
    public void addPlayerPlots(List<PlotModel> playerPlots, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        int playerId = getPlayerId(playerName);
        try {
            preparedStatement = dbConnection.prepareStatement(ADD_PLOTS_QUERY);
            for (PlotModel plot : playerPlots) {
                preparedStatement.setInt(1, plot.getDaysOld());
                preparedStatement.setInt(2, plot.getDaysSinceWater());
                preparedStatement.setString(3, plot.getCropInPlot().getCropName());
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
     * Adjusts the days old the plot is as days pass by.
     *
     * @param cropName   The name of the crop in the plot.
     * @param playerName The player whose plots need to be updated.
     */
    public void adjustPlotDaysOld(String cropName, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        int playerId = getPlayerId(playerName);
        try {
            preparedStatement = dbConnection.prepareStatement(UPDATE_PLOT_MATURITY);
            preparedStatement.setString(1, cropName);
            preparedStatement.setInt(2, playerId);
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
