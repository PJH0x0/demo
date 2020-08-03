import java.util.*;
class Solution {
    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<String>();
        Set<String> result = new HashSet<>();
        result.add("()");
        result = generateParenthesis2(result, 1, n);
        return new ArrayList<String>(result);
    }

    public Set<String> generateParenthesis2(Set<String> last, int index, int n) {
        if (index == n) return last;
        //System.out.println("bracket " + index + " = " + last);
        Set<String> cur = new HashSet<>(last.size() * 2);
        for (String bracket : last) {
            cur.add("()" + bracket);
            cur.add(bracket + "()");
            cur.add("(" + bracket + ")");
        }
        return generateParenthesis2(cur, index + 1, n);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) System.out.println("test[" + i + "] = " + new Solution().generateParenthesis(i));

        String[] error = new String[] {
            "()()()()","(()())()","(()(()))","()()(())","(())()()","(((())))","()((()))","()(())()","()(()())","(()()())","((()()))","((()))()","((())())"
        };
        String[] succeed = new String[] {
            "(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"
        };
        List<String> errList = Arrays.asList(error);
        List<String> sucList = Arrays.asList(succeed);
        for(String suc : sucList) {
            if (!errList.contains(suc)) System.out.println("not add " + suc);
        }
    }
}
