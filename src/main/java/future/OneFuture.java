package future;

import java.util.Random;
import java.util.concurrent.*;

public class OneFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new CallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt(100);
            // return null;
        }
    }
}
