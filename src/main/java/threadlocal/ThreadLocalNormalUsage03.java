package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 加锁解决线程安全问题
 * @author BaiJY
 * @date 2023/07/10
 **/
public class ThreadLocalNormalUsage03 {

    public static ExecutorService threadpool = Executors.newFixedThreadPool(10);
    //非线程安全
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        String s = null;
        synchronized (ThreadLocalNormalUsage03.class) {
            s = dateFormat.format(date);
        }
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======");
        ExecutorService threadpool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadpool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage03().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadpool.shutdown();
    }
}
