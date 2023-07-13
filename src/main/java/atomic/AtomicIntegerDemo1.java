package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BaiJY
 * @date 2023/07/13
 **/
public class AtomicIntegerDemo1 implements Runnable {
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
    }

    private static volatile int basicCount = 0;

    public void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 atomicIntegerDemo1 = new AtomicIntegerDemo1();
        Thread thread = new Thread(atomicIntegerDemo1);
        Thread thread1 = new Thread(atomicIntegerDemo1);
        thread.start();
        //必须两个线程争抢才会出现线程不安全的问题
        thread1.start();
        //必须等待子线程执行完毕才能获取到最终结果
        thread.join();
        thread1.join();
        System.out.println("atomic=====" + atomicInteger.get());
        System.out.println("basicCount==========" + basicCount);
    }
}
