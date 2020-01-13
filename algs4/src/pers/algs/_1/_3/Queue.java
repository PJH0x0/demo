package pers.algs._1._3;

import edu.princeton.cs.algs4.StdOut;

public class Queue<T> {
    private Node head;
    private Node last;
    private class Node {
        Node next;
        T data;
    }
    public void enqueue(T value) {
        Node tmp = last;
        last = new Node();
        last.data = value;
        if (isEmpty()) head = last;
        else tmp.next = last;
        //Before opt
        /*if (isEmpty()) {
            last = new Node();
            last.data = value;
            head = last;
        } else {
            Node tmp = last;
            last = new Node();
            last.data = value;
            tmp.next = last;
        }*/
    }
    public T dequeue() {
        if (isEmpty()) return null;
        T value = head.data;
        head = head.next;//if last node, head.next will be null
        if (isEmpty()) last = null;
        return value;
        //Before opt
        /*if (isEmpty()) return null;
        T value = head.data;
        //Last one node
        if (head == last) {
            head = last = null;
        } else {
            head = head.next;
        }
        return value;*/
    }
    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        StdOut.println("isEmpty >> " + queue.isEmpty());
        queue.enqueue("abc");
        queue.enqueue("def");
        queue.enqueue("xyz");
        StdOut.println("isEmpty2 >> " + queue.isEmpty());
        
        StdOut.println("dequeue >> " + queue.dequeue());
        StdOut.println("dequeue >> " + queue.dequeue());
        StdOut.println("dequeue >> " + queue.dequeue());
        StdOut.println("isEmpty3 >> " + queue.isEmpty());
        StdOut.println("dequeue >> " + queue.dequeue());
    }
}
