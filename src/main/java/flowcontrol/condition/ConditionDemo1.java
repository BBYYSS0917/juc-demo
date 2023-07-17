package flowcontrol.condition;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() {
        lock.lock();
        try {
            System.out.println("ConditionDemo1.method1 await");
            condition.await();
            System.out.println("ConditionDemo1.method1 continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void method2() {
        lock.lock();
        try {
            System.out.println("ConditionDemo1.method2 signal");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        
    }
}
