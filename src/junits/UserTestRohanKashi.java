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
        typePlayer = new PlayerModel(100);
        this.userTest = new UserModel(typePlayer);
    }

    //Test getter method for UserModel
    @Test
    public void testGetSeedType() {
        assertEquals(typePlayer, this.userTest.getPlayerModel());
    }

    //Test setter method for UserModel
    @Test
    public void testSetSeedType() {
        typePlayer.setUserCurrentMoney(500);
        assertEquals(500, this.userTest.getPlayerModel().getUserCurrentMoney());
    }

}
