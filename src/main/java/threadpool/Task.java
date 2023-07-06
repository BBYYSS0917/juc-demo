package threadpool;

/**
 * @author BaiJY
 * @date 2023/07/06
 **/
public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
