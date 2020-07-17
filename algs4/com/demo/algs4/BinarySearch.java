package com.demo.algs4;

public class BinarySearch {

    int[] orderedArray;
    public BinarySearch(int[] orderedArray) {
        this.orderedArray = orderedArray;
    }
    public int search(int key) {
        if (null == orderedArray || orderedArray.length <= 0) {
            return -1;
        }
        /*int low = 0;
        int high = orderedArray.length - 1;
        while(low <= high) {
            System.out.println("low>>" + low + "  high>>" + high);
            int mid = (high + low) / 2;
            if (key < orderedArray[mid] ) high = mid - 1;
            else if (key > orderedArray[mid]) low = mid + 1;
            else return mid;
        }*/
        int mid = (orderedArray.length - 1) / 2;
        while(mid >= 0 && mid < orderedArray.length) {
            System.out.println("mid>>" + mid);
            int midValue = orderedArray[mid];
            System.out.println("midValue>>" + midValue);
            if (midValue < key) mid = (mid + 1 + orderedArray.length) / 2 ;
            else if (midValue > key) mid = mid / 2 - 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int search = new BinarySearch(array).search(10);

        System.out.println("search>>" + search);
    }
}
