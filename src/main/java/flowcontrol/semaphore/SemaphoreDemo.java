package flowcontrol.semaphore;

import java.util.concurrent.*;

public class SemaphoreDemo {
    //信号量的获取和释放是可以跨线程的，这是它与synchronized的一个重要区别，synchronized是不可以跨线程的
    static Semaphore semaphore = new Semaphore(3,true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 100; i++) {
            service.submit(new Task());
        }
        service.shutdown();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " is running");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is over");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
