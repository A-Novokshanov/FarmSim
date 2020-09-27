package junits.milestone2;

import models.PlayerModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTestAdityaPratap {

    private PlayerModel playerModel;

    @Before
    public void setUp() {
        this.playerModel = new PlayerModel(250);
    }

    @Test
    public void testGetCurrentMoney() {
        int expected = 250;
        int actual = this.playerModel.getUserCurrentMoney();

        assertEquals(expected, actual);
    }

    @Test
    public void testSetCurrentMoney() {
        int currentMoney = 345;
        this.playerModel.setUserCurrentMoney(currentMoney);

        assertEquals(currentMoney, this.playerModel.getUserCurrentMoney());
    }
}
