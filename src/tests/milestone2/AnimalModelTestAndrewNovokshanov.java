package tests.milestone2;

import models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrew Novokshanov anovokshanov3@gatech.edu
 * @version 1.0
 */
public class AnimalModelTestAndrewNovokshanov {

    private AnimalModel animalModelTest;
    private int purchasePrice;
    private int sellPrice;
    private int timeToGrow;
    private String animalType;
    @Before
    public void setup() {
        purchasePrice = 100;
        sellPrice = 200;
        timeToGrow = 15;
        animalType = "Pig";
        animalModelTest = new AnimalModel(purchasePrice, sellPrice, timeToGrow, animalType);
    }

    //Test getter method for purchasePrice
    @Test
    public void testGetterPurchasePrice() {
        assertEquals(animalModelTest.getPurchasePrice(), purchasePrice);
    }
    //Test setter method for purchasePrice
    @Test
    public void testSetterPurchasePrice() {
        animalModelTest.setPurchasePrice(150);
        assertEquals(animalModelTest.getPurchasePrice(), 150);
    }
    //Test getter method for sellPrice
    @Test
    public void testGetterSellPrice() {
        assertEquals(animalModelTest.getSellPrice(), sellPrice);
    }
    //Test setter method for sellPrice
    @Test
    public void testSetterSellPrice() {
        animalModelTest.setSellPrice(50);
        assertEquals(animalModelTest.getSellPrice(), 50);
    }
    //Test getter method for timeToGrow
    @Test
    public void testGetterTimeToGrow() {
        assertEquals(animalModelTest.getTimeToGrow(), timeToGrow);
    }
    //Test setter method for timeToGrow
    @Test
    public void testSetterTimeToGrow() {
        animalModelTest.setTimeToGrow(500);
        assertEquals(animalModelTest.getTimeToGrow(), 500);
    }
    //Test getter method for animalType
    @Test
    public void testGetterAnimalType() {
        assertEquals(animalModelTest.getAnimalType(), animalType);
    }
    //Test setter method for animalType
    @Test
    public void testSetterAnimalType() {
        animalModelTest.setAnimalType("Cow");
        assertEquals(animalModelTest.getAnimalType(), "Cow");
    }
}
