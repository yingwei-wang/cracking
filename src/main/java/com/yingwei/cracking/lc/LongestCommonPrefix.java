package com.yingwei.cracking.lc;

public class LongestCommonPrefix {

    // solution: vertical check
    // Time: O(S), s=m*n;
    // Space: O(1)

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c || strs[j].length() == i) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }


    // solution: horizontal check

    // solution: divide and conquer

    // solution: Binary search
}
