package viewmodels;

import models.PlayerModel;
import models.WorkerModel;
import services.WorkerService;


/**
 * This view-model class controls the logic and flow of workers on the farm.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class WorkerViewModel {

    private PlayerModel player;
    private WorkerService workerService;

    /**
     * Constructor for worker view model/
     *
     * @param player is the player.
     */
    public WorkerViewModel(PlayerModel player) {
        this.workerService = new WorkerService();
        this.player = player;
    }

    /**
     * Method to upgrade the type of the worker
     *
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
        switch (worker.getWorkerType()) {
        case 0:
            worker.setWorkerType(1);
            worker.setWorkerWage(10);
            break;
        case 1:
            worker.setWorkerType(2);
            worker.setWorkerWage(20);
            break;
        default:
            break;
        }
    }

    /**
     * Method to pay the wage of the worker
     *
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
            workerLeaves(workerModel);
            return;
        } else {
            player.setUserCurrentMoney(currMoney - workerWage);
        }
    }

    /**
     * Method to make the worker leave
     * @param workerModel worker model
     */
    public void workerLeaves(WorkerModel workerModel) {
        workerModel.setWorkerType(0);
        workerModel.setWorkerWage(0);
    }

    /**
     * Current Price to upgrade worker
     *
     * @param worker the worker model to check the upgrade price of
     * @return the price to upgrade the worker from its current tier
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

    /**
     * Adds the worker to the database.
     *
     * @param workerModel The worker model to be added.
     */
    public void addWorkerDatabase(WorkerModel workerModel) {
        workerService.addWorker(workerModel.getWorkerType(), workerModel.getWorkerWage());
    }

    /**
     * Updates the worker wage in the database.
     *
     * @param workerModel The worker model class that contains the new wage.
     */
    public void updateWorkerWageDatabase(WorkerModel workerModel) {
        workerService.adjustWorkerWage(workerModel.getWorkerWage());
    }

    /**
     * @param workerModel The worker model class the contains the new type.
     */
    public void updateWorkerTypeDatabase(WorkerModel workerModel) {
        workerService.adjustWorkerType(workerModel.getWorkerType());
    }

    public WorkerModel getWorkerDatabase() {
        return workerService.queryWorker();
    }
}
