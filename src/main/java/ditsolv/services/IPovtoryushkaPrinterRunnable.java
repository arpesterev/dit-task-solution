package ditsolv.services;

import ditsolv.models.DoneCounts;

import java.util.List;

public interface IPovtoryushkaPrinterRunnable extends Runnable {

    void addTask(DoneCounts task);
    List<DoneCounts> executedTasks();

}
