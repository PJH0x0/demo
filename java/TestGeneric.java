import java.util.*;
public class TestGeneric {

    public static class Pair<T> {
        public Pair() {
            System.out.println("contruct " );
        }
        public T func(T t) {
            return t;
        }
    }
    public static void main(String[] args) {
        Pair<Integer> p = new Pair<Integer>();
        System.out.println(p.func("123"));
    }
}
