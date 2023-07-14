package immutable;

public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a = "wukong2";

        //因为是final，编译器会进行优化，直接把b替换成"wukong"
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
