package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal解决线程安全问题
 *
 * @author BaiJY
 * @date 2023/07/10
 **/
public class ThreadLocalNormalUsage04 {

    public static ExecutorService threadpool = Executors.newFixedThreadPool(10);
    //非线程安全
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======");
        ExecutorService threadpool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadpool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage04().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadpool.shutdown();
    }
}


class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
}
