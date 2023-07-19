package cache.computable;

public class MayFail implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.1) {
            throw new Exception("计算失败");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
