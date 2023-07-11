package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author BaiJY
 * @date 2023/07/11
 **/
public class MustUnlock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //lock保护的资源
        }finally {
            //Lock不会在异常的时候自动释放，必须有finally释放的过程
            lock.unlock();
        }
    }
}
