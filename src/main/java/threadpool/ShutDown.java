package threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author BaiJY
 * @date 2023/07/06
 **/
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
        Thread.sleep(1500);
        executorService.shutdown();
        executorService.isShutdown();
        executorService.execute(new Task());

        //线程是否完全停止
        System.out.println(executorService.isTerminated());

        //阻塞，等待线程池中的所有线程执行完毕
        executorService.awaitTermination(3, TimeUnit.SECONDS);

        //线程池立即关闭，返回队列中未执行的任务
        List<Runnable> runnableList = executorService.shutdownNow();

    }

    class ShutDownTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("被中断了");
            }
        }
    }
}
