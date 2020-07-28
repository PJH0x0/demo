
public class DeleteDuplicate {
    public int deleteDuplicate(int[] a) {
        if (null == a || a.length == 0) {
            return 0;
        }
        int i = 0;
        int j = i+1;
        int k = 0;
        while(j < a.length) {
            if (a[i] == a[j]) {
                j++;
                continue;
            } else {
                a[++k] = a[j];
                i = j++;
            }
        }
        return k+1;
    }

    public static void main(String[] args) {
        DeleteDuplicate demo = new DeleteDuplicate();
        int[] a = new int[] {0, 0,  1, 1, 2, 2, 3, 3, 4, 4, 5 };
        int newLen = demo.deleteDuplicate(a);
        System.out.println("new array length -> " + newLen);
        for(int i = 0; i < newLen; i++) {
            System.out.println("a[" + i + "] = " + a[i]);
        }
    }
}
