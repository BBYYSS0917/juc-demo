package cache.computable;

public class ExpensiveFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        // assume it costs a lot of time
        Thread.sleep(5000);
        return Integer.valueOf(arg);
    }
}
