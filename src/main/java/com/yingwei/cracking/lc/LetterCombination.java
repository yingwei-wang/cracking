package com.yingwei.cracking.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LetterCombination {

    private String[] charmap = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    //solution 1: recursive
    public List<String> solution1(String digits) {
        List<String> ans = new ArrayList<>();
        combination("", digits, 0, ans);
        return ans;
    }

    private void combination(String prefix, String digits, int position, List<String> ans) {
        if (position == digits.length()) {
            ans.add(prefix);
            return;
        }

        String letters = charmap[digits.charAt(position) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, position + 1, ans);
        }
    }


    //solution 2: recursive
    public List<String> solution2(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();

        List<String> ans = new ArrayList<>();
        List<String> subCombinations = solution2(digits.substring(1));
        String letters = charmap[digits.charAt(0) - '0'];
        for (Character c : letters.toCharArray()) {
            if (subCombinations.isEmpty()) {
                ans.add(c.toString());
            } else {
                for (String sub : subCombinations) {
                    ans.add(c + sub);
                }
            }
        }

        return ans;
    }


    //solution 2: queue
    public List<String> solution3(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        ans.add("");
        while (ans.peek().length() != digits.length()) {
            String pre = ans.remove();
            String letters = charmap[digits.charAt(pre.length()) - '0'];

            for (Character c : letters.toCharArray()) {
                ans.add(pre + c);
            }
        }
        return ans;
    }


}
