package pers.algs._1._3;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Stack<T> implements Iterable<T> {

    private static class Node<T> {
        Node<T> next;
        T data;
    }

    private Node<T> top;
    private int count;
    public void push(T value) {
        Node<T> tmp = top;
        top = new Node<T>();
        top.next = tmp;
        top.data = value;
        count++;
        /*if (null == top)  {
            top = new Node<T>();
            top.data = value;
        } else {
            Node<T> tmp = new Node<T>();
            tmp.data = value;
            tmp.next = top;
            top = tmp;
        }
        count++;*/
    }
    public T pop() {
        if (null == top) return null;
        T value = top.data;
        top = top.next;
        return value;
        /*if (null == top) return null;
        Node<T> tmp = top.next;
        T value = top.data;
        top.next = null;
        top = tmp;
        count--;
        return value;*/
    }
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator<T>(top);
    }

    private static class StackIterator<T> implements Iterator<T> {
        private Node<T> current;
        public StackIterator(Node<T> top) {
            current = top;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public T next() {
            if (null == current) return null;
            T value = current.data;
            current = current.next;
            return value;
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        StdOut.println("empty >> " + stack.isEmpty());
        stack.push("1234");
        stack.push("5678");
        stack.push("90");
        StdOut.println("empty >> " + stack.isEmpty());
        Iterator<String> iterator = stack.iterator();
        while(iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        StdOut.println("pop >> " + stack.pop());

        while(iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        Iterator<String> iterator2 = stack.iterator();
        while(iterator2.hasNext()) {
            StdOut.println("Update iterator >> " + iterator2.next());
        }

    }
}
