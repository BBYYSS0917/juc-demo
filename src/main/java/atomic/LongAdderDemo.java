package atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(counter));
        }

        // Thread.sleep(10000);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Thread.sleep(10);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(counter.sum());
    }

    private static class Task implements Runnable {
        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }

}
