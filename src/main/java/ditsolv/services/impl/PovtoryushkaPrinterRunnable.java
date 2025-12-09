package ditsolv.services.impl;

import ditsolv.models.DoneCounts;
import ditsolv.services.IPovtoryushkaPrinterRunnable;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PovtoryushkaPrinterRunnable implements IPovtoryushkaPrinterRunnable {

    private final List<DoneCounts> currentNotSolvedTasks = new ArrayList<>();
    private final List<DoneCounts> currentDone = new ArrayList<>();

    @Override
    public void run() {
        while (true) {
            synchronized (currentNotSolvedTasks) {
                currentNotSolvedTasks
                        .stream()
                        .filter(
                                el -> el.getDone() < el.getUserRequest().getCountRepeats()
                                        && Instant.now().isAfter(el.getNextTimeToCall())
                        )
                        .forEach(
                                notDoneTask -> {
                                    System.out.println(notDoneTask.getUserRequest().getText());
                                    notDoneTask.getTotalPrintedText().append(notDoneTask.getUserRequest().getText());
                                    // Что с состоянием
                                    notDoneTask.setDone(notDoneTask.getDone() + 1);
                                    // Когда потом вызывать?
                                    notDoneTask.setNextTimeToCall(
                                            // Текущий таймаут
                                            notDoneTask
                                                    .getNextTimeToCall()
                                                    .plusMillis(
                                                            // Какую задержку просил пользователь в миллисекундах
                                                            notDoneTask
                                                                    .getUserRequest()
                                                                    .getDelayedTimeout()
                                                    )
                                    );
                                    currentDone.add(
                                            new DoneCounts(
                                                    notDoneTask.getUserRequest(),
                                                    notDoneTask.getDone(),
                                                    notDoneTask.getNextTimeToCall()
                                            )
                                    );
                                }
                        );
            }
        }
    }

    @Override
    public void addTask(DoneCounts task) {
        synchronized (currentNotSolvedTasks) {
            currentNotSolvedTasks.add(task);
        }
    }

    @Override
    public List<DoneCounts> executedTasks() {
        return currentDone;
    }
}
