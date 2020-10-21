package com.yingwei.cracking.lc;

public class MaxSubarray {

    public int maxSubArray(int[] nums) {
        int max = nums[0], max_end_here = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max_end_here = Math.max(nums[i], max_end_here + nums[i]);
            max = Math.max(max, max_end_here);
        }

        return max;
    }
}
