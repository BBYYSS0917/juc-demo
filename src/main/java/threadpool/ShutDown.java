package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author BaiJY
 * @date 2023/07/06
 **/
public class ShutDown {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
        Thread.sleep(1500);
        executorService.shutdown();
        executorService.isShutdown();
        executorService.execute(new Task());
    }

    class ShutDownTask implements Runnable {
        @Override
        public void run() {

        }
    }
}
