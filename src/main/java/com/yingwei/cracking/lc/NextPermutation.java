package com.yingwei.cracking.lc;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        revert(nums, i + 1);
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void revert(int[] nums, int start) {
        int i = start, j = nums.length - 1;

        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
