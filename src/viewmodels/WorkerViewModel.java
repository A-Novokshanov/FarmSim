package viewmodels;

import models.PlayerModel;
import models.WorkerModel;


/**
 * This view-model class controls the logic and flow of workers on the farm.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class WorkerViewModel {

    private PlayerModel player;
    private WorkerModel workerModel;

    /**
     * Constructor for worker view model/
     *
     * @param workerModel the worker model who uses these plots.
     * @param player      is the player.
     */
    public WorkerViewModel(WorkerModel workerModel, PlayerModel player) {
        this.workerModel = workerModel;
        this.player = player;
    }

    /**
     * Method to upgrade the type of the worker
     * @param worker the worker model to try to upgrade the tier of
     */
    public void upgradeWorker(WorkerModel worker) {
        double currMoney = player.getUserCurrentMoney();
        int upgradePrice = upgradePrice(worker);
        String difficulty = player.getPlayerSettings().getStartingDifficulty();
        double workerWageMulti = 1.0;
        switch (difficulty) {
            case "Casual":
                workerWageMulti = 0.8;
                break;
            case "Normal":
                workerWageMulti = 1.0;
                break;
            case "Veteran":
                workerWageMulti = 1.2;
                break;
            default:
                break;
        }
        if (!checkPurchasableUpgrade(upgradePrice)) {
            return;
        }
        switch (workerModel.getWorkerType()) {
        case 0:
            workerModel.setWorkerType(1);
            workerModel.setWorkerWage(10);
            break;
        case 1:
            workerModel.setWorkerType(2);
            workerModel.setWorkerWage(20);
            break;
        default:
            break;
        }
    }

    /**
     * Method to pay the wage of the worker
     * @param workerModel the worker model
     */
    public void payWorker(WorkerModel workerModel) {
        if (workerModel.getWorkerType() == 0) {
            return;
        }
        double currMoney = player.getUserCurrentMoney();
        String difficulty = player.getPlayerSettings().getStartingDifficulty();
        double workerWageMulti = 1.0;
        switch (difficulty) {
        case "Casual":
            workerWageMulti = 0.8;
            break;
        case "Normal":
            workerWageMulti = 1.0;
            break;
        case "Veteran":
            workerWageMulti = 1.2;
            break;
        default:
            break;
        }
        double workerWage = workerModel.getWorkerWage() * workerWageMulti;
        if (currMoney < workerWage) {
            workerLeaves();
            return;
        } else {
            player.setUserCurrentMoney(currMoney - workerWage);
        }
    }

    /**
     * Method to make the worker leave
     */
    public void workerLeaves() {
        workerModel.setWorkerType(0);
        workerModel.setWorkerWage(0);
    }

    /**
     * Current Price to upgrade worker
     * @param worker the worker model to check the upgrade price of
     */
    public int upgradePrice(WorkerModel worker) {
        int type = worker.getWorkerType();
        if (type == 0) {
            return 250;
        } else if (type == 1) {
            return 500;
        }
        return 0;
    }

    /**
     * This methods checks if the user can upgrade the worker
     * <p>
     * 1. Check if the user has enough money.
     * 2. Check if user has enough enough storage space.
     * </p>
     *
     * @param upgradeCost price of upgrade
     * @return A boolean representing if a user can purchase an item.
     */
    public boolean checkPurchasableUpgrade(double upgradeCost) {
        String difficulty = player.getPlayerSettings()
                .getStartingDifficulty();
        return (player.getUserCurrentMoney() >= upgradeCost);
    }
}