package com.yingwei.cracking.lc;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> ans = new ArrayList<>();

        if (k < 0 || k > n) return ans;

        if (k == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        ans = combine(n - 1, k - 1);

        for (List<Integer> list : ans) {
            list.add(n);
        }

        ans.addAll(combine(n - 1, k));

        return ans;
    }
}
