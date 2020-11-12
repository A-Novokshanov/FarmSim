package tests.milestone5;

import javafx.concurrent.Worker;
import models.WorkerModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthew Farias mfarias6@gatech.edu
 * @version 1.0
 */
public class WorkerModelMatthewFarias {
    WorkerModel workerModel1 = new WorkerModel();
    WorkerModel workerModel2 = new WorkerModel();
    WorkerModel workerModel3 = new WorkerModel();

    @Before
    public void setUp() {

    }

    @Test
    public void testSetWorkerType() {
        workerModel1.setWorkerType(1);
        assertEquals(workerModel1.getWorkerType(), 1);
        workerModel2.setWorkerType(2);
        assertEquals(workerModel2.getWorkerType(), 2);
        workerModel3.setWorkerType(3);
        assertEquals(workerModel3.getWorkerType(), 3);
    }

    @Test
    public void testSetWorkerWage() {
        workerModel1.setWorkerWage(1);
        assertEquals(workerModel1.getWorkerWage(), 1);
        workerModel2.setWorkerWage(2);
        assertEquals(workerModel2.getWorkerWage(), 2);
        workerModel3.setWorkerWage(3);
        assertEquals(workerModel3.getWorkerWage(), 3);
    }
}
