package tests.milestone4;

import org.junit.Before;
import org.junit.Test;
import services.DatabaseConnection;
import services.authentication.LoginPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains the Junits for the LoginPlayerService class.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class LoginPlayerServiceTestAdityaPratap {
    private LoginPlayer loginPlayer;
    private static final String GET_USER_ID_MONEY = "SELECT id, money, days from player where name=?";
    private PreparedStatement preparedStatement;

    @Before
    public void setUp() {
        this.loginPlayer = new LoginPlayer();
    }

    @Test
    public void testUserID() {

        Connection dbConnection = DatabaseConnection.getDbConnection();
        try {
            preparedStatement = dbConnection.prepareStatement(GET_USER_ID_MONEY);
            preparedStatement.setString(1, "test");
            ResultSet resultSet = preparedStatement.executeQuery();
            int playerId = resultSet.getInt("id");
            assertEquals(1, playerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testPlayerExists() {
        boolean actual = loginPlayer.checkUserExists("test");
        assertTrue(actual);

    }

    @Test
    public void testPlayerMoney() {
        double actual = loginPlayer.queryPlayerMoneyAndSettings("test").getUserCurrentMoney();
        double expected = 1000.0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testPlayerDays() {
        int actual = loginPlayer.queryPlayerMoneyAndSettings("test").getDays();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testPlayerDifficulty() {
        String actual = loginPlayer.queryPlayerMoneyAndSettings("test").getPlayerSettings().getStartingDifficulty();
        String expected = "Normal";
        assertEquals(expected, actual);
    }

    @Test
    public void testPlayerName() {
        String actual = loginPlayer.queryPlayerMoneyAndSettings("test").getPlayerSettings().getPlayerName();
        String expected = "test";
        assertEquals(expected, actual);
    }

}
