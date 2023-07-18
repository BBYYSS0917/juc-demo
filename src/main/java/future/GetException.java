package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.submit(new CallableTask1());
        Future<Integer> future = service.submit(new CallableTask1());
        try {
            //即使执行任务时发生异常，也只会在get()方法中抛出异常，而不会影响线程池中其他线程的执行
            future.get();
        } catch (InterruptedException e) {
            System.out.println("11111");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("22222");
            throw new RuntimeException(e);
        }
        service.shutdown();
    }

    static class CallableTask1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
