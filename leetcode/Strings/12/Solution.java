import java.util.*;
class Solution {
    public Solution() {
    }
    public String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        StringBuilder roman = new StringBuilder();
        int times = num;
        for(int i = 1000; i > 1; i = i / 10) {
            if ((times = num / i) != 0) {
                num = num % i;
                for(int j = 0; j < times; j++) roman.append(map.get(i));
            }
            if (num >= (i - i/10)) {
                num = num % (i - i/10);
                roman.append(map.get(i / 10)).append(map.get(i));
            }
            if (num >= (i/2)) {
                num = num % (i/2);
                roman.append(map.get(i/2));
            }
            if (num >= (i/2 - i/10)) {
                num = num % (i/2 - i/10);
                roman.append(map.get(i/10)).append(map.get(i/2));
            }
        }
        for(int i = 0; i < num; i++) roman.append(map.get(1));
        return roman.toString();
    }

    public static void main(String[] args) {
        int[] test = new int[] {
            3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                58,
                100,
                400,
                500,
                900,
                1000,
                1994,
                1995,
                3999,
        };
        for(int i = 0; i < test.length; i++) {
            System.out.println("test[" + i + "] = " + new Solution().intToRoman(test[i]));
        }
    }
}
