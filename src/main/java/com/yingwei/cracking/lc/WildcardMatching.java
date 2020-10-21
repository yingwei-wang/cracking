package com.yingwei.cracking.lc;

public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0;
        int starIdx = -1;
        int starMatchedIdx = -1;

        while (si < s.length()) {

            if (pi < p.length() && s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') {
                si++;
                pi++;
            } else if (pi < p.length() && p.charAt(pi) == '*') {
                starIdx = pi;
                starMatchedIdx = si;
                pi++;
            } else if (starIdx != -1) {
                pi = starIdx + 1;
                starMatchedIdx++;
                si = starMatchedIdx;
            } else {
                return false;
            }
        }

        while (pi < p.length() && p.charAt(pi) == '*') {
            pi++;
        }

        return pi == p.length();


    }
}
