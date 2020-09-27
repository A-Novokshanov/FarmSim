package junits.milestone2;

import models.SeedModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeedTestRohanKashi {
    private SeedModel seedModel;

    @Before
    public void setup() {
        this.seedModel = new SeedModel("banana");
    }

    //Test getter method for SeedModel
    @Test
    public void testGetSeedType() {
        assertEquals("banana", this.seedModel.getSeedType());
    }

    //Test setter method for SeedModel
    @Test
    public void testSetSeedType() {
        this.seedModel.setSeedType("apple");
        assertEquals("apple", this.seedModel.getSeedType());
    }

}
