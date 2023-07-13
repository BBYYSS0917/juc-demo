package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author BaiJY
 * @date 2023/07/13
 **/
public class AtomicArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);

        Thread[] threadsInc = new Thread[100];
        Thread[] threadsDec = new Thread[100];

        for (int i = 0; i < 100; i++) {
            // Runnable target;
            threadsInc[i] = new Thread(incrementer);
            threadsDec[i] = new Thread(decrementer);

            threadsInc[i].start();
            threadsDec[i].start();
        }

        for (int i = 0; i < 100; i++) {
            // join each thread
            try {
                threadsInc[i].join();
                threadsDec[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // verify each element is 0 or not
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("We have a problem" + i);
                System.out.println(atomicIntegerArray.get(i));
            }
        }

        System.out.println("Everything is OK");
    }

    static class Decrementer implements Runnable {
        private AtomicIntegerArray array;

        public Decrementer(AtomicIntegerArray array) {
            this.array = array;
        }

        @Override
        public void run() {
            for (int i = 0; i < array.length(); i++) {
                array.getAndDecrement(i);
            }
        }
    }

    static class Incrementer implements Runnable {
        private AtomicIntegerArray array;

        public Incrementer(AtomicIntegerArray array) {
            this.array = array;
        }

        @Override
        public void run() {
            for (int i = 0; i < array.length(); i++) {
                array.getAndIncrement(i);
            }
        }
    }
}
