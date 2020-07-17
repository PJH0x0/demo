package com.sample;
class Sample {
    public interface Test {
        public void method(String s);
    }
    public static void main(String[] args) {
        Test test = (s)->System.out.println(s + "world");
        test.method("Hello ");
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println("a>>>" + a);
        System.out.println("b>>>" + b);
        if (a == b) {
            System.out.println("True");   
        } else {
            System.out.println("false");
        }
        Float c = Float.valueOf(1.0f - 0.9f);
        Float d = Float.valueOf(0.9f - 0.8f);
        System.out.println("c>>>" + c);
        System.out.println("d>>>" + d);
        if (c == d) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        String param = null;
        switch(param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
        System.out.println("Hello world");
    }
}
