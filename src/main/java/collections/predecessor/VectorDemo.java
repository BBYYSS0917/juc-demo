package collections.predecessor;
import java.util.Vector;
/**
 * vector类似于ArrayList，但是是线程安全的，效率低
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("hello");
        System.out.println(vector.get(0));
    }
}
