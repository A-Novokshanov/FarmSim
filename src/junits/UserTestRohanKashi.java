package junits;

import models.UserModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTestRohanKashi {
    private UserModel userModel;

    @Before
    public void setup() {
        this.userModel = new UserModel("admin");
    }

    //Test getter method for SeedModel
    @Test
    public void testGetSeedType() {
        assertEquals("admin", this.userModel.getUserType());
    }

    //Test setter method for SeedModel
    @Test
    public void testSetSeedType() {
        this.userModel.setUserType("player");
        assertEquals("player", this.userModel.getUserType());
    }

}
