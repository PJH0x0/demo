package pers.algs._1._1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Practice {
    public static class Seven {
        public Seven() {
            double t = 9.0;
            while (Math.abs(t - 9.0/t) > .001) {
                StdOut.printf("%.5f\n", t);
                t = (9.0/t + t) / 2.0;
            }
            StdOut.printf("%.5f\n", t);
        }
    }
    private static class Thirteen {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {'a', 'b', 'c', 'd'}};
        int[][] b = new int[4][3];

        public Thirteen() {
            for (int i = 0; i < 3; i++)
                for(int j = 0; j < 4; j++) {
                    b[j][i] = a[i][j];
                }
            for (int i = 0; i < 4; i++)
                for(int j = 0; j < 3; j++) 
                    StdOut.println(b[i][j]);
        }
    }

    private static void PracticeNineteen() {
        long[] a = new long[100];
        a[0] = 0;
        a[1] = 1;
        for(int i = 2; i < 100; i++) {
            a[i] = a[i-1] + a[i-2];
            StdOut.println("a[" + i + "] >> " + a[i]);
        }
    }
    static int reverseCount = 0;
    private static double binomial(int N, int k, double p) {
        reverseCount++;
        if (N == 0 && k == 0) {
            //StdOut.println("N == 0, k == 0");
            return 1.0;
        }
        if (N < 0 || k < 0) {
            //StdOut.println(" N>> " + N + " k>> " + k);
            return 0.0;
        }
        double temp = (1.0 - p) * binomial(N-1, k, p) + p*binomial(N-1, k-1, p);
        //StdOut.println("tmp>> " + temp + " N>> " + N + " k>> " + k);
        return temp;
    }
    private static double binomial2(int N, int k, double p) {
        double[][] result = new double[N][k];
        result[0][0] = 1.0;
        result[0][1] = 0.0;
        for (int i = 1; i < N; i++) {
            result[i][0] = (1.0 - p) * result[i-1][0];
            for (int j = 1; j < k; j++) {
                result[i][j] = (1.0 - p) * result[i-1][j] + p * result[i-1][j-1];
                //StdOut.println("result[" + i + "][" + j + "] = " + result[i][j]);
            }
        }
        return result[N-1][k-1];
    }

    public static void main(String[] args) {
        //StdOut.println("Practice >>" + Seven.class.getName());
        //new Seven();

        //StdOut.println("Practice >>" + Thirteen.class.getName());
        //new Thirteen();

        //PracticeNineteen();
        StdOut.println("Practice 27 binomial>> " + binomial(10, 5, 0.25));
        StdOut.println("reverseCount>>" + reverseCount);
        StdOut.println("Practice 27 binomial2>> " + binomial2(101, 51, 0.25));
    }
}
