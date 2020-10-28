package tests.milestone4;

import org.junit.Before;
import org.junit.Test;
import services.DatabaseConnection;
import services.player.PlayerPlotService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerPlotServiceTestRohan {
    private static final String GET_PLOT_WATER = "SELECT a.watervalue FROM plot a WHERE a.player = ? AND "
            + "a.identifier = ?";
    private PlayerPlotService playerPlotService;

    @Before
    public void setUp() {
        this.playerPlotService = new PlayerPlotService();
    }

    @Test
    public void testGetPlayerID() {
        assertEquals(playerPlotService.getPlayerId("test"), 1);

    }

    @Test
    public void testIsDBConnected() {
        Connection dbConnection = DatabaseConnection.getDbConnection();
        assertTrue(playerPlotService.isDbConnected(dbConnection));
    }

    @Test
    public void testupdateWaterValue() {
        playerPlotService.updateWaterValue(3, "test", 1651);
        Connection dbConnection = DatabaseConnection.getDbConnection();
        int playerId = playerPlotService.getPlayerId("test");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dbConnection.prepareStatement(GET_PLOT_WATER);
            preparedStatement.setInt(1, playerId);
            preparedStatement.setInt(2, 1651);
            ResultSet resultSet = preparedStatement.executeQuery();
            int waterValue = resultSet.getInt("watervalue");
            assertEquals(6, waterValue);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
