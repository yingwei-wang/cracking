package com.yingwei.cracking.lc;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i] <= nums.length) {
                if (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                    int tmp = nums[i];
                    nums[i] = nums[tmp - 1];
                    nums[tmp - 1] = tmp;
                    continue;
                }
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) return j + 1;
        }

        return nums.length + 1;
    }
}
