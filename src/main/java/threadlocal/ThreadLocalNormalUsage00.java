package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaiJY
 * @date 2023/07/10
 **/
public class ThreadLocalNormalUsage00 {


    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println("=======");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("begin running");
                String date = new ThreadLocalNormalUsage00().date(1007);
                System.out.println(date);
            }
        }).start();
    }
}
