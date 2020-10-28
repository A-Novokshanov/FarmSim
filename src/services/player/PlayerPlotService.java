package services.player;

import models.CropModel;
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
    private static final String ADD_PLOTS_QUERY = "INSERT INTO plot(days, water, crop, player, identifier, watervalue) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_ID_QUERY = "SELECT a.id FROM player a WHERE a.name = ?";
    private static final String UPDATE_PLOT_MATURITY = "UPDATE plot SET days = days + 1, water = water + 1 "
            + "WHERE crop = ? AND player = ?";
    private static final String REMOVE_PLOT_QUERY = "DELETE FROM plot WHERE identifier = ? AND player = ?";
    private static final String UPDATE_WATER_VALUE = "UPDATE plot SET watervalue = watervalue + ? WHERE player = ? AND identifier = ?";
    private static final String GET_USER_PLOTS = "SELECT * FROM plot where player=?";



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
     * This is to get player id.
     *
     * @param playerName is the specific player we want.
     * @return the player id.
     */
    public int getPlayerId(String playerName) {
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
     * Updates the player plot water value.
     *
     * @param waterValue the water value to update.
     * @param playerName the player who needs the update.
     * @param identifier the plot we want.
     */
    public void updateWaterValue(int waterValue, String playerName, int identifier) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        int playerId = getPlayerId(playerName);
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(UPDATE_WATER_VALUE);
                preparedStatement.setInt(1, waterValue);
                preparedStatement.setInt(2, playerId);
                preparedStatement.setInt(3, identifier);
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
                preparedStatement.setInt(5, plot.getPlotIdentifier());
                preparedStatement.setInt(6, plot.getWaterValue());
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

     public List<PlotModel> queryPlayerPlots(String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        List<PlotModel> myList = new java.util.ArrayList<>();
        int playerId = getPlayerId(playerName);
        ResultSet resultSet = null;
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(GET_USER_PLOTS);
                preparedStatement.setInt(1, playerId);
                resultSet = preparedStatement.executeQuery();
                // Populate a PlotModel for every row in the database
                // add the plotModel to the list
                // return the list
                while (resultSet.next()) {
                    int days = resultSet.getInt("days");
                    int water = resultSet.getInt("water");
                    String cropName = resultSet.getString("crop");
                    double cropValue = 0;
                    if (cropName.equals("Corn")) {
                        cropValue = 100.00;
                    } else if (cropName.equals("Tomato")) {
                        cropValue = 60.00;
                    } else if (cropName.equals("Potato")) {
                        cropValue = 40.00;
                    }
                    int plotIdentifier = resultSet.getInt("identifier");

                    CropModel crop = new CropModel(cropName, 1, cropValue);
                    PlotModel plotModel = new PlotModel(crop, days);
                    plotModel.setDaysSinceWater(water);
                    plotModel.setPlotIdentifier(plotIdentifier);
                    myList.add(plotModel);
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
        return myList;
    }

    /**
     * Adds a single plot for the player.
     *
     * @param plot       the plot we want to add.
     * @param playerName the player who needs the plot added.
     */
    public void addPlot(PlotModel plot, String playerName) {

        Connection dbConnection = DatabaseConnection.getDbConnection();
        int playerId = getPlayerId(playerName);
        if (isDbConnected(dbConnection)) {
            try {
                preparedStatement = dbConnection.prepareStatement(ADD_PLOTS_QUERY);
                preparedStatement.setInt(1, plot.getDaysOld());
                preparedStatement.setInt(2, plot.getDaysSinceWater());
                preparedStatement.setString(3, plot.getCropInPlot().getCropName());
                preparedStatement.setInt(4, playerId);
                preparedStatement.setInt(5, plot.getPlotIdentifier());
                preparedStatement.setInt(6, plot.getWaterValue());
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

    /**
     * Deletes a plot from the database when the player harvests a plot.
     *
     * @param plotIdentifier The unique identifier assigned to each plot for access.
     * @param playerName     The name of the player whose plots need to be removed.
     */
    public void deletePlot(int plotIdentifier, String playerName) {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        int playerId = getPlayerId(playerName);
        try {
            preparedStatement = dbConnection.prepareStatement(REMOVE_PLOT_QUERY);
            preparedStatement.setInt(1, plotIdentifier);
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
