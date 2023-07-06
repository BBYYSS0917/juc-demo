package threadpool;

import java.util.concurrent.Executors;

/**
 * @author BaiJY
 * @date 2023/07/06
 **/
public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new EveryTaskOneThread.Task());
            thread.start();
        }
//        Executors.newFixedThreadPool()
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("执行了子任务");
        }
    }
}
