package com.yingwei.cracking.lc;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {

        int ret = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c) + 1);
            }
            ret = Math.max(ret, j - i + 1);
            map.put(c, j);
        }

        return ret;
    }

    public static void main(String[] args) {

        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        int ret = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(ret);

    }
}
