package com.yingwei.cracking.lc;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        boolean[] used = new boolean[nums.length];
        List<Integer> perm = new ArrayList<Integer>();
        permHelper(nums, used, perm, ret);
        return ret;
    }

    private void permHelper(int[] nums, boolean[] used, List<Integer> perm, List<List<Integer>> ret) {
        if (perm.size() == nums.length) {
            ret.add(new ArrayList<Integer>(perm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                perm.add(nums[i]);
                used[i] = true;
                permHelper(nums, used, perm, ret);
                used[i] = false;
                perm.remove(perm.size() - 1);
            }
        }
    }


    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0) {
            return ret;
        }

        ret.add(new ArrayList<Integer>());

        for(int i = 0; i < nums.length; i++) {

            List<List<Integer>> curr = new ArrayList<List<Integer>>();

            for (List<Integer> perm : ret) {
                for (int j = 0; j <= perm.size(); j++) {
                    perm.add(j, nums[i]);
                    List<Integer> copy = new ArrayList<Integer>(perm);
                    curr.add(copy);
                    perm.remove(j);
                }
            }

            ret = curr;
        }

        return ret;

    }
}
