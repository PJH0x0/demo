package pers.algs._1._2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;

public class Practice {

    public static void one(int N) {
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.random();
            double y = StdRandom.random();
            points[i] = new Point2D(x, y);
        }
    }

    public static void main(String[] args) {
        StdOut.println("***Pracetice 1.2***");
        one(100);
    }
}
