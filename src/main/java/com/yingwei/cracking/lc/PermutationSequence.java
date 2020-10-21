package com.yingwei.cracking.lc;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    public String getPermutation(int n, int k) {
        StringBuffer sb = new StringBuffer();

        //building factorial lookup
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        //
        k--; //everything starts with 0;

        for (int i = 1; i <= n; i++) {
            int idx = k / factorial[n - i];
            sb.append(numbers.remove(idx));
            k = k % factorial[n - i];
        }

        return sb.toString();
    }
}
