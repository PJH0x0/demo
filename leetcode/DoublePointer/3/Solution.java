
/**
3. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
import java.util.HashMap;
public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || "".equals(s)) return 0;
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int i = 0;
        int j = 0;
        int n = s.length();

        while (i < n) {
            char ch = s.charAt(i);
            Integer index = map.get(ch);
            if (null != index) {
                max = Math.max(max, i - j);
                j = Math.max(index + 1, j);
            } 
            map.put(ch, i++);
        }
        max = Math.max(max, i - j);
         return max;
    }

    public static void main(String[] args) {
        String s1 = "bbbbb";
        String s2 = "abcabcbb";
        String s3 = "bd";
        String s4 = "dvdf";
        String s5 = "abba";
        String s6 = "addfasklfjskafjdkslajfskafjkasfsajdklfsadl";

        System.out.println("s1 = " + lengthOfLongestSubstring(s1));
        System.out.println("s2 = " + lengthOfLongestSubstring(s2));
        System.out.println("s3 = " + lengthOfLongestSubstring(s3));
        System.out.println("s4 = " + lengthOfLongestSubstring(s4));
        System.out.println("s5 = " + lengthOfLongestSubstring(s5));
    }
}
