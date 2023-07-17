package flowcontrol.countdownlatch;

import java.util.concurrent.*;

public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("NO." + no + "准备完毕，等待发令枪");
                    try {
                        latch.await();
                        System.out.println("NO." + no + "开始跑步了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            service.submit(runnable);
        }

        Thread.sleep(5000);
        latch.countDown();
        service.shutdown();
    }

}
