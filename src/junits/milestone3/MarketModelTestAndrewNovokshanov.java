package junits.milestone3;

import models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrew Novokshanov anovokshanov3@gatech.edu
 * @version 1.0
 */
public class MarketModelTestAndrewNovokshanov {

    private MarketModel marketModelTest;
    private CropModel[] cropsInMarketSummer;
    private CropModel corn;
    private CropModel potato;
    private CropModel tomato;
    private CropModel orange;

    @Before
    public void setup() {
        corn = new CropModel("Corn", 1, 100.0);
        potato = new CropModel("Potato", 15, 3.0);
        tomato = new CropModel("Tomato", 131, 55.0);
        orange = new CropModel("Orange", 0, 1500.3);
        CropModel[] cropsInMarket = new CropModel[]{corn, potato, tomato};
        cropsInMarketSummer = new CropModel[]{corn, potato, orange};
        marketModelTest = new MarketModel(cropsInMarket);
    }

    //Test getter method for getCropsInMarket
    @Test
    public void testGetterCropsInMarket() {
        assertEquals(marketModelTest.getCropsInMarket()[0], corn);
        assertEquals(marketModelTest.getCropsInMarket()[0].getCropName(), "Corn");
        assertEquals(marketModelTest.getCropsInMarket()[0].getCropQuantity(), 1);
        assertEquals(marketModelTest.getCropsInMarket()[0].getCropValue(), 100.0, 0.0);
        assertEquals(marketModelTest.getCropsInMarket()[1], potato);
        assertEquals(marketModelTest.getCropsInMarket()[1].getCropName(), "Potato");
        assertEquals(marketModelTest.getCropsInMarket()[1].getCropQuantity(), 15);
        assertEquals(marketModelTest.getCropsInMarket()[1].getCropValue(), 3.0, 0.0);
        assertEquals(marketModelTest.getCropsInMarket()[2], tomato);
        assertEquals(marketModelTest.getCropsInMarket()[2].getCropName(), "Tomato");
        assertEquals(marketModelTest.getCropsInMarket()[2].getCropQuantity(), 131);
        assertEquals(marketModelTest.getCropsInMarket()[2].getCropValue(), 55.0, 0.0);
    }
    //Test setter method for getCropsInMarket
    @Test
    public void testSetterCropsInMarket() {
        marketModelTest.setCropsInMarket(cropsInMarketSummer);
        assertEquals(marketModelTest.getCropsInMarket()[2], orange);
        assertEquals(marketModelTest.getCropsInMarket()[2].getCropName(), "Orange");
        assertEquals(marketModelTest.getCropsInMarket()[2].getCropQuantity(), 0);
        assertEquals(marketModelTest.getCropsInMarket()[2].getCropValue(), 1500.3, 0.0);
    }
}