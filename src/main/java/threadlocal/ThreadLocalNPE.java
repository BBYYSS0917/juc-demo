package threadlocal;

/**
 * @author BaiJY
 * @date 2023/07/10
 **/
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public long get() {
        //注意拆箱问题引发的空指针问题
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.longThreadLocal.get()); //会触发空指针异常
        threadLocalNPE.set();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println("=====");
                System.out.println(threadLocalNPE.longThreadLocal.get());
            }
        }).start();
        System.out.println(threadLocalNPE.longThreadLocal.get());
    }
}
