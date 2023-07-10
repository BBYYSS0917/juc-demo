package threadlocal;

/**
 * @author BaiJY
 * @date 2023/07/10
 **/
public class ThreadLocalNormalUsage05 {
    public static void main(String[] args) {
//        Thread
        new Service1().process();
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1 {
    public void process() {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
    }
}

class Service2 {
    public void process() {
//        User user = new User("超哥");
        UserContextHolder.holder.get();
    }
}

class Service3 {
    public void process() {
//        User user = new User("超哥");
        UserContextHolder.holder.get();
    }
}


class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}