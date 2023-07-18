package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OneShotLatch {

    private final Sync sync = new Sync();

    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }


    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            // 如果闭锁是开的（state == 1），那么这个操作将成功，否则将失败
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            // 打开闭锁
            setState(1);
            // 现在其他线程可以获取该闭锁
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                        System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                        oneShotLatch.await();
                        System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
                }
            }).start();
        }
        Thread.sleep(5000);
        oneShotLatch.signal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                oneShotLatch.await();
                System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
            }
        }).start();
    }
}
