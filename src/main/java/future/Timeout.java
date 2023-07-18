package future;

import java.util.concurrent.*;

public class Timeout {
    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断期间的默认广告");
            }
            return new Ad("正常广告");
        }
    }

    public void printAd() {
        long start = System.currentTimeMillis();
        Future<Ad> f = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = f.get(4000, TimeUnit.SECONDS);
            System.out.println(ad);
        } catch (TimeoutException e) {
            System.out.println("Timeout...");
            boolean cancel = f.cancel(false);
        } catch (Exception e) {
            ad = DEFAULT_AD;
            System.out.println(ad);
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
    }
}
