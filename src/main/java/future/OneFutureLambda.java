package future;

import java.util.Random;
import java.util.concurrent.*;

public class OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt(100);
        };
        Future<Integer> future = executorService.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        executorService.shutdown();
    }

//    static class CallableTask implements Callable<Integer> {
//        @Override
//        public Integer call() throws Exception {
////            Thread.sleep(3000);
////            return new Random().nextInt(100);
//            // return null;
//        }
//    }
}
