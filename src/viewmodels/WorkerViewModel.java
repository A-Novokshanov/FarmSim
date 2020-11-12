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
     */
    public void upgradeWorker() {
        switch (workerModel.getWorkerType()) {
        case 0:
            if (player.getUserCurrentMoney() > 500) {
                workerModel.setWorkerType(1);
                workerModel.setWorkerWage(10);
                player.setUserCurrentMoney(player.getUserCurrentMoney() - 500);
            }
            break;
        case 1:
            if (player.getUserCurrentMoney() > 1000) {
                workerModel.setWorkerType(2);
                workerModel.setWorkerWage(20);
                player.setUserCurrentMoney(player.getUserCurrentMoney() - 1000);
            }
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


}