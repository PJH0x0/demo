package pers.algs._1._1;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
    private static int countRank1 = 0;
    private static int countRank2 = 0;
    private static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        countRank1++;
        int mid = (lo + hi) / 2;
        if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else if (key < a[mid]) return rank(key, a, lo, mid - 1);
        else return mid;
    }
    private static int rank2(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi) {
            countRank2++;
            int mid = (lo + hi) / 2;
            if (key > a[mid]) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] a = new int[100000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
        int key = 100000;
        StdOut.println("rank>> " + key + "  " + rank(key, a, 0, a.length - 1) + " count>>" + countRank1);
        StdOut.println("rank2>> " + key + "  " + rank2(key, a) + " count2>>" + countRank2);
    }
}
