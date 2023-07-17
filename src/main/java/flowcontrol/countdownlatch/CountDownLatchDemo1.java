package flowcontrol.countdownlatch;

// import java.util.concurrent.CountDownLatch;
// import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;

public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    try {
                        Thread.sleep((long) Math.random() * 10000);
                        System.out.println("NO." + no + " is over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            service.submit(runnable);
        }
        System.out.println("Waiting for all threads to finish");
        latch.await();
        System.out.println("All threads are finished");
    }
}
