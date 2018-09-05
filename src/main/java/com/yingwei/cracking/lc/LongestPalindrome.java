package com.yingwei.cracking.lc;

public class LongestPalindrome {


    /**
     * expand from center.
     * <p>
     * Time: O(n^2)
     * Space: O(1)
     */

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        int maxLen = 0;
        int L = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = findPalindrome(s, i, i);
            int len2 = findPalindrome(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                L = i - (len - 1) / 2;
            }
        }

        return s.substring(L, L + maxLen);
    }


    private int findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }


}
