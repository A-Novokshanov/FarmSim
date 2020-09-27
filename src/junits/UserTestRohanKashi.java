package junits;

import models.PlayerModel;
import models.UserModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTestRohanKashi {
    private PlayerModel typePlayer;
    private UserModel userTest;

    @Before
    public void setup() {
        typePlayer = new PlayerModel();
        this.userTest = new UserModel(typePlayer);
    }

    //Test getter method for UserModel
    @Test
    public void testGetUserType() {
        assertEquals(typePlayer, this.userTest.getPlayerModel());
    }

    //Test setter method for UserModel
    @Test
    public void testSetUserType() {
        PlayerModel newPlayer = new PlayerModel();
        userTest.setPlayerModel(newPlayer);
        assertEquals(newPlayer, this.userTest.getPlayerModel());
    }

}
