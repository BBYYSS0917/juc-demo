package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author BaiJY
 * @date 2023/07/06
 **/
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}
