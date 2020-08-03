public class Solution {
    public int romanToInt(String s) {
        if (null == s || "".equals(s)) return 0;
        char roman = '0';
        char negRo = '0';
        int sum = 0;
        for(int i = 0; i < s.length(); i++) {
            roman = s.charAt(i);
            switch (roman) {
                case 'I':
                    if (i < s.length() - 1) {
                        if (s.charAt(i+1) == 'V') {
                            i++;
                            sum += 4;
                        } else if (s.charAt(i+1) == 'X') {
                            i++;
                            sum += 9;
                        } else {
                            sum += 1;
                        }
                    } else {
                        sum += 1;
                    }
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X': 
                    if (i < s.length() - 1) {
                        if (s.charAt(i+1) == 'L') {
                            i++;
                            sum += 40;
                        } else if (s.charAt(i+1) == 'C') {
                            i++;
                            sum += 90;
                        } else {
                            sum += 10;
                        }
                    } else {
                        sum += 10;
                    }
                    break;

                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    if (i < s.length() - 1) {
                        if (s.charAt(i+1) == 'D') {
                            i++;
                            sum += 400;
                        } else if (s.charAt(i+1) == 'M') {
                            i++;
                            sum += 900;
                        } else {
                            sum += 100;
                        }
                    } else {
                        sum += 100;
                    }
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
                default:
                    return 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] test = new String[] {
            "III",
                "IV",
                "IX",
                "LVIII",
                "MCMXCIV",
                "MCMXCIVV",
        };
        for(int i = 0; i < test.length; i++) {
            int sum = new Solution().romanToInt(test[i]);
            System.out.println("test[" + i + "] = " + sum);
        }
    }
}
