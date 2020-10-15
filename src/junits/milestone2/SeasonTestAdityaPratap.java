package junits.milestone2;

import models.AnimalModel;
import models.CropModel;
import models.SeasonModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SeasonTestAdityaPratap {

    private SeasonModel seasonModel;
    private AnimalModel goat;
    private AnimalModel chicken;
    private AnimalModel cow;
    private CropModel tomatoCrop;
    private CropModel cornCrop;
    private List<AnimalModel> animalModelList;
    private List<CropModel> cropModelList;

    @Before
    public void setUp() {
        this.goat = new AnimalModel(120, 150, 50, "Goat");
        this.chicken = new AnimalModel(50, 63, 28, "Chicken");
        this.cow = new AnimalModel(560, 846, 150, "Cow");

        this.tomatoCrop = new CropModel("Tomato", 50, 23.45);
        this.cornCrop = new CropModel("Corn", 65, 12.76);

        this.animalModelList = new ArrayList<>();
        this.animalModelList.add(this.goat);
        this.animalModelList.add(this.chicken);
        this.animalModelList.add(this.cow);

        this.cropModelList = new ArrayList<>();
        this.cropModelList.add(this.tomatoCrop);
        this.cropModelList.add(this.cornCrop);

        this.seasonModel = new SeasonModel(2, "Spring", animalModelList, cropModelList);
    }

    //Test getter method for plantGrowthModifier
    @Test
    public void testGetPlantGrowthModifier() {

        int expected = 2;
        int actual = this.seasonModel.getPlantGrowthModifier();

        assertEquals(expected, actual);
    }

    //Test getter method for testGetSeasonType
    @Test
    public void testGetSeasonType() {

        String expected = "Spring";
        String actual = this.seasonModel.getSeasonType();

        assertEquals(expected, actual);
    }

    //Test getter method for getDesirableAnimals
    @Test
    public void testGetDesirableAnimals() {
        List<AnimalModel> expectedList = new ArrayList<>();
        List<AnimalModel> actualList = this.seasonModel.getDesirableAnimals();

        AnimalModel goat = new AnimalModel(120, 150, 50, "Goat");
        AnimalModel chicken = new AnimalModel(50, 63, 28, "Chicken");
        AnimalModel cow = new AnimalModel(560, 846, 150, "Cow");

        expectedList.add(goat);
        expectedList.add(chicken);
        expectedList.add(cow);

        assertEquals(expectedList.get(0).getAnimalType(), actualList.get(0).getAnimalType());
        assertEquals(expectedList.get(1).getPurchasePrice(), actualList.get(1).getPurchasePrice());
        assertEquals(expectedList.get(2).getTimeToGrow(), actualList.get(2).getTimeToGrow());
    }

    //Test getter method for desirableCrops
    @Test
    public void testGetDesirableCrops() {

        List<CropModel> expectedList = new ArrayList<>();
        List<CropModel> actualList = this.seasonModel.getDesirableCrops();

        CropModel tomatoCrop = new CropModel("Tomato", 50,  23.45);
        CropModel cornCrop = new CropModel("Corn", 65, 12.76);

        expectedList.add(tomatoCrop);
        expectedList.add(cornCrop);

        assertEquals(expectedList.get(0).getCropName(), actualList.get(0).getCropName());
        assertEquals(expectedList.get(1).getCropValue(), actualList.get(1).getCropValue(), 0.5);

    }

    //Test setter method for plantGrowthModifier
    @Test
    public void testSetPlantGrowthModifier() {
        int plantGrowthModifier = 3;
        this.seasonModel.setPlantGrowthModifier(plantGrowthModifier);

        int actual = this.seasonModel.getPlantGrowthModifier();

        assertEquals(plantGrowthModifier, actual);
    }

    //Test setter method setSeasonType
    @Test
    public void testSetSeasonType() {
        String seasonType = "Winter";
        this.seasonModel.setSeasonType(seasonType);

        String actual = this.seasonModel.getSeasonType();

        assertSame(seasonType, actual);
    }

    //Test setter method for desirableAnimals
    @Test
    public void testSetDesirableAnimals() {

        List<AnimalModel> animals = new ArrayList<>();
        AnimalModel sheep = new AnimalModel(65, 70, 28, "Sheep");
        AnimalModel horse = new AnimalModel(250, 300, 120, "Horse");

        animals.add(sheep);
        animals.add(horse);

        this.seasonModel.setDesirableAnimals(animals);

        assertEquals(animals, this.seasonModel.getDesirableAnimals());

    }

    //Test setter method for desirableCrops
    @Test
    public void testSetDesirableCrops() {

        List<CropModel> crops = new ArrayList<>();
        CropModel wheatCrop = new CropModel("Wheat", 70,  15.00);

        crops.add(wheatCrop);
        this.seasonModel.setDesirableCrops(crops);

        assertEquals(crops, this.seasonModel.getDesirableCrops());

    }


}
