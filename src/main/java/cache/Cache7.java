package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;
import cache.computable.MayFail;

import java.util.concurrent.*;

public class Cache7<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Cache7(Computable<A, V> c) {
        this.c = c;
    }

    public static void main(String[] args) {
        Cache7<String, Integer> cache2 = new Cache7<>(new MayFail());
        try {
            System.out.println("开始计算");
            Integer result = cache2.compute("13");
            System.out.println("第一次计算结果：" + result);
            result = cache2.compute("13");
            System.out.println("第二次计算结果：" + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public V compute(A arg) {
        while (true) {
            //可见性保证了同一个key不会被计算多次
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(callable);
                //ConcurrentHashMap的putIfAbsent()方法是原子的，保证了同一个key不会被多次计算
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    System.out.println("从FutureTask调用了计算函数");
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                System.out.println("被取消了");
                cache.remove(arg);
                e.printStackTrace();
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                cache.remove(arg);
                throw new RuntimeException(e);
            }
        }
    }
}
