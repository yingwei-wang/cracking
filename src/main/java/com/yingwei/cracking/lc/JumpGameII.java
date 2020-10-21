package com.yingwei.cracking.lc;

public class JumpGameII {

    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int step = 0;
        int curMaxReach = 0;
        int nextMaxReach = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            nextMaxReach = Math.max(nextMaxReach, i + nums[i]);

            if (i == curMaxReach) {
                step++;
                curMaxReach = nextMaxReach;
            }
        }

        return step;
    }


    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int maxReach = nums[0];
        for (int i = 0; i <= maxReach && i < nums.length; i++) {
            if (i + nums[i] >= nums.length - 1) {
                return true;
            } else {
                maxReach = Math.max(maxReach, i + nums[i]);
            }
        }

        return false;
    }
}
