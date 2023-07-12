package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BaiJY
 * @date 2023/07/12
 **/
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    public synchronized void testMethod() {
        a++;
    }
}
