package com.yingwei.cracking.lc;

public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] == target) return mi;

            if (nums[lo] < nums[mi]) {
                if (target >= nums[lo] && target < nums[mi]) {
                    hi = mi - 1;
                } else {
                    lo = mi + 1;
                }
            } else {
                if (target > nums[mi] && target <= nums[hi]) {
                    lo = mi + 1;
                } else {
                    hi = mi - 1;
                }
            }
        }

        return -1;
    }
}
