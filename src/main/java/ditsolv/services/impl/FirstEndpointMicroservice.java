package ditsolv.services.impl;

import ditsolv.configurations.PovtoryushkaConfig;
import ditsolv.dtos.PrintMe;
import ditsolv.models.DoneCounts;
import ditsolv.services.IPovtoryushkaPrinterRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ditsolv.services.IFirstEndpointMicroservice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FirstEndpointMicroservice implements IFirstEndpointMicroservice {

    private final PovtoryushkaConfig povtoryushkaConfig;

    private int threadId = 0;
    private final List<IPovtoryushkaPrinterRunnable> printers;
    private final List<Thread> threadGroup;

    public FirstEndpointMicroservice(PovtoryushkaConfig config) {
        log.info("Got config: {}", config);
        this.povtoryushkaConfig = config;
        this.printers = new ArrayList<>();
        this.threadGroup = new ArrayList<>();

        for (int i = 0; i < config.getThreadCount(); i++) {
            var printer = new PovtoryushkaPrinterRunnable();
            this.printers.add(printer);
            this.threadGroup.add(new Thread(printer) {{
                start();
            }});
        }
    }

    @Override
    public void povtoryushka(PrintMe obj) {
        DoneCounts objectToExecute = new DoneCounts(obj, 0, Instant.now().plusMillis(obj.getDelayedTimeout()));
        synchronized (this) {
            int threadIdx = nextThreadId();
            log.info("Got nextThreadId = {}", threadIdx);
            printers.get(threadIdx).addTask(objectToExecute);
        }
        // Проверяем до посинения
        while (objectToExecute.getDone() < objectToExecute.getUserRequest().getCountRepeats()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized int nextThreadId() {
        int currentThreadId = threadId;
        currentThreadId += 1;
        if (currentThreadId >= povtoryushkaConfig.getThreadCount()) {
            // Берём по модулю, чтобы оставаться в границах [0, TOTAL)
            currentThreadId = currentThreadId % povtoryushkaConfig.getThreadCount();
        }
        // ЗАПИШУ
        this.threadId = currentThreadId;
        // ВЕРНУ
        return currentThreadId;
    }

    @Override
    public synchronized List<DoneCounts> getDoneTasks() {
        var result = new ArrayList<DoneCounts>();
        printers.stream().map(IPovtoryushkaPrinterRunnable::executedTasks).forEach(result::addAll);
        return result;
    }
}
