package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;

import java.util.HashMap;

public class Cache3<A, V> implements Computable<A, V> {
    private final HashMap<A, V> cache = new HashMap<>();

    private final Computable<A, V> c;

    public Cache3(Computable<A, V> c) {
        this.c = c;
    }

    public static void main(String[] args) {
        Cache3<String, Integer> cache2 = new Cache3<>(new ExpensiveFunction());
        try {
            System.out.println("开始计算");
            Integer result = cache2.compute("13");
            System.out.println("第一次计算结果：" + result);
            result = cache2.compute("13");
            System.out.println("第二次计算结果：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return null;
    }
}
