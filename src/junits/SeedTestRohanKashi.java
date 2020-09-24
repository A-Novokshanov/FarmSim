package junits;

import models.SeedModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeedTestRohanKashi {
    private SeedModel seedModel;
    @Before
    public void setup() {
        this.seedModel = new SeedModel("seedModel");
    }

    @Test
    public void testGetSeedType() {
        assertEquals("seedModel", this.seedModel.getSeedType());
    }

    @Test
    public void testSetSeedType() {
        this.seedModel.setSeedType("something");
        assertEquals("something", this.seedModel.getSeedType());
    }

}
