package junits;

import models.SeedModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeedTestRohanKashi {
    private SeedModel seedModel;
    @Before
    public void setup() {
        this.seedModel = new SeedModel("bannana");
    }

    @Test
    public void testGetSeedType() {
        assertEquals("bannana", this.seedModel.getSeedType());
    }

    @Test
    public void testSetSeedType() {
        this.seedModel.setSeedType("apple");
        assertEquals("apple", this.seedModel.getSeedType());
    }

}
