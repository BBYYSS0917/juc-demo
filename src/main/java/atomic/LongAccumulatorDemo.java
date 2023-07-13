package atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class LongAccumulatorDemo {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        IntStream.range(1, 10).forEach(i -> executorService.submit(() -> longAccumulator.accumulate(i)));

        executorService.shutdown();
        while(!executorService.isTerminated()) {
            // Thread.sleep(10);
        }

        longAccumulator.accumulate(1);

        System.out.println(longAccumulator.getThenReset());
    }
}
