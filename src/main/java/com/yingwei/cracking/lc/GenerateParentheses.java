package com.yingwei.cracking.lc;

        import java.util.ArrayList;
        import java.util.List;

public class GenerateParentheses {

    public List<String> solution1(int n) {
        List<String> ans = new ArrayList<>();
        backtrack("", n, n, ans);
        return ans;
    }

    private void backtrack(String current, int left, int right, List<String> ans) {

        if (left == 0 && right == 0) {
            ans.add(current);
            return;
        }

        if (left > 0) {
            backtrack(current + "(", left - 1, right, ans);
        }

        if (left < right) {
            backtrack(current + ")", left, right - 1, ans);
        }
    }
}
