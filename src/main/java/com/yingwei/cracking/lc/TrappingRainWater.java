package com.yingwei.cracking.lc;

public class TrappingRainWater {

    public int trap(int[] height) {
        if (height == null && height.length == 0) return 0;

        int n = height.length;
        int[] lMax = new int[n];
        int[] rMax = new int[n];

        int lm = height[0];
        for (int i = 0; i < n; i++) {
            if (height[i] > lm) {
                lm = height[i];
            }
            lMax[i] = lm;
        }

        int rm = height[n - 1];
        for (int j = n - 1; j >= 0; j--) {
            if (height[j] > rm) {
                rm = height[j];
            }
            rMax[j] = rm;
        }

        int ans = 0;
        for (int k = 0; k < n; k++) {
            ans += Math.max(lMax[k], rMax[k]) - height[k];
        }

        return ans;
    }
}
