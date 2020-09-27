package junits;

import models.FarmModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Matthew Farias Email:mfarias6@gatech.edu
 * @version 1.0
 */
public class FarmModelTestMatthewFarias {
    private FarmModel farmModel;
    private int day;


    @Before
    public void setup() {
        day = 1;
        farmModel = new FarmModel(day);
    }

    /**
     * Test for the farm day getter in FarmModel class.
     */
    @Test
    public void testGetFarmDay() {
        assertEquals(farmModel.getFarmDay(), 1);
    }

    /**
     * Test for the farm day setter in FarmModel class.
     */
    @Test
    public void testSetFarmModel() {
        farmModel.setFarmDay(2);
        assertEquals(farmModel.getFarmDay(), 2);
    }
}
