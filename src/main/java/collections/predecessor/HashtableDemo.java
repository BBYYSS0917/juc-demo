package collections.predecessor;

import java.util.Hashtable;

public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("hello", "world");
        System.out.println(hashtable.get("hello"));
    }
}
