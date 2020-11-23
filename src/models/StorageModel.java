package models;


import java.util.ArrayList;

/**
 * This class stores the data related to the cropInventory,
 * and also adds and removes crops from cropInventory.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class StorageModel {
    private ArrayList<CropModel> cropInventory;


    private ArrayList<PlotModel> plotInventory;
    private int totalFertilizer;
    private int totalPesticide;

    /**
     * Constructor for the cropInventory that creates the
     * structure of the cropInventory and initializes it with starter crops.
     */
    public StorageModel() {
        cropInventory = new ArrayList<>();
        cropInventory.add(new CropModel("Corn", 5, 100.00));
        cropInventory.add(new CropModel("Potato", 5, 80.00));
        cropInventory.add(new CropModel("Tomato", 5, 60.00));
        CropModel cornPest = new CropModel("Corn with Pesticide", 2, 70.00);
        cornPest.setHasPesticide(true);
        CropModel potatoPest = new CropModel("Potato with Pesticide", 2, 50.00);
        potatoPest.setHasPesticide(true);
        CropModel tomatoPest = new CropModel("Tomato with Pesticide", 2, 30.00);
        tomatoPest.setHasPesticide(true);
        cropInventory.add(cornPest);
        cropInventory.add(potatoPest);
        cropInventory.add(tomatoPest);
        totalFertilizer = 3;
        totalPesticide = 3;
        plotInventory = new ArrayList<>();
    }

    /**
     * This method sets the inventory.
     *
     * @param cropInventory The list of crops to be set.
     */
    public void setCropInventory(ArrayList<CropModel> cropInventory) {
        this.cropInventory = cropInventory;
    }

    /**
     * Gets the cropInventory that the player currently has.
     *
     * @return the cropInventory.
     */
    public ArrayList<CropModel> getInventory() {
        return cropInventory;
    }

    /**
     * Adds to the current crop amount.
     *
     * @param i        the index our object is in.
     * @param quantity how much of the crop to add.
     */
    public void setNewCropAmount(int quantity, int i) {
        cropInventory.get(i).setCropQuantity(cropInventory.get(i).getCropQuantity() + quantity);
    }

    /**
     * Sets up a new crop in our list if its
     *
     * @param crop     the crop we want to add.
     * @param quantity how much of the crop to add.
     */
    public void setNewCrop(CropModel crop, int quantity) {
        cropInventory.add(cropInventory.size(), crop);
        crop.setCropQuantity(crop.getCropQuantity() + quantity);
    }


    /**
     * Looks to see if we have the crop in the storage and then
     * removes a certain quantity, if not all of it.
     *
     * @param quantity how much of the crop to remove.
     * @param i        is the location of crop in inventory
     */
    public void removeCropAmount(int quantity, int i) {
        cropInventory.get(i).setCropQuantity(cropInventory.get(i).getCropQuantity() - quantity);
    }

    /**
     * Removes the crop from the inventory.
     *
     * @param i where its located in our list.
     */
    public void removeCrop(int i) {
        cropInventory.remove(i);
    }

    /**
     * Checks to see if we have to remove from inventory, or just a certain amount.
     *
     * @param i        location of crop.
     * @param quantity amount to remove.
     * @return what scenario to go with.
     */
    public int getEnoughToRemove(int i, int quantity) {
        if (cropInventory.get(i).getCropQuantity() - quantity > 0) {
            return 1;
        } else if (cropInventory.get(i).getCropQuantity() - quantity == 0) {
            return 2;
        }
        return 3;
    }

    /**
     * Gets the current cropInventory size.
     *
     * @return cropInventory size.
     */
    public int getTotalCropAmount() {
        int count = 0;
        for (CropModel crop : cropInventory) {
            count += crop.getCropQuantity();
        }
        count -= 12;
        return count;

    }

    /**
     * BLANK
     *
     * @return blank.
     */
    public int getInventorySize() {
        return cropInventory.size();
    }

    /**
     * Checks to see if the crop name matches with the current object in list.
     *
     * @param index the current index in the list.
     * @param crop2 the crop passed in to add to inventory.
     * @return whether the names are equal.
     */
    public boolean checkIfNameCorrect(int index, CropModel crop2) {
        return cropInventory.get(index).getCropName().equals(crop2.getCropName());
    }

    /**
     * The capacity of our inventory.
     *
     * @return the capacity.
     */
    public int getCapacity() {
        return 15;
    }

    /**
     * Updating the total fertilizer variable
     *
     * @param totalFertilizer new totalFertilizer value to set
     */
    public void setTotalFertilizer(int totalFertilizer) {
        this.totalFertilizer = totalFertilizer;
    }

    /**
     * Updating the total pesticide variable
     *
     * @param totalPesticide new totalPesticide value to set
     */
    public void setTotalPesticide(int totalPesticide) {
        this.totalPesticide = totalPesticide;
    }

    /**
     * Getter for the total fertilizer variable
     *
     * @return the total fertilizer count
     */
    public int getTotalFertilizer() {
        return totalFertilizer;
    }

    /**
     * Getter for the total pesticide variable
     *
     * @return the total pesticide count
     */
    public int getTotalPesticide() {
        return totalPesticide;
    }

    public ArrayList<PlotModel> getPlotInventory() {
        return plotInventory;
    }

    public void setPlotInventory(ArrayList<PlotModel> plotInventory) {
        this.plotInventory = plotInventory;
    }

    public void addPlotToStorage(PlotModel purchasedPlot) {
        plotInventory.add(purchasedPlot);
    }
}
