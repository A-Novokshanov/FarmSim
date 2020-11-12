package models;
/**
 The WorkerModel class represents the worker class
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class WorkerModel {
    private int type;
    private int wage;

    /** Constructs a WorkerModel object with specified Type.
     */
    public WorkerModel() {
        this.type = 0;
        this.wage = 0;
    }

    /** @return Gets and returns the Type attribute of a WorkerModel object. */
    public int getWorkerType() {
        return this.type;
    }

    /** Sets the type of a UserModel.
     @param type is the new desired type of a UerModel object.
     */
    public void setWorkerType(int type) {
        this.type = type;
    }

    /** @return Gets and returns the wage attribute of a WorkerModel object. */
    public int getWorkerWage() {
        return this.wage;
    }

    /** Sets the wage of a UserModel.
     @param wage is the new desired wage of a UerModel object.
     */
    public void setWorkerWage(int wage) {
        this.wage = wage;
    }
}
