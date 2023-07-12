package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author BaiJY
 * @date 2023/07/12
 **/
public class LockInterruptibly implements Runnable {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread0 = new Thread(lockInterruptibly);
        Thread thread1 = new Thread(lockInterruptibly);

        thread0.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread0.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("睡眠期间被中断了");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }

        } catch (InterruptedException e) {
//            System.out.println(Thread.currentThread().getName() + "获取锁期间被中断了");
            System.out.println("获取锁期间被中断了");
            e.printStackTrace();
        }
    }
}
