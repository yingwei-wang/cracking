package com.yingwei.cracking.lc;

public class AddTwoNumber {


    /**
     * Complexity Analysis
     * <p>
     * Time complexity : O(\max(m, n))O(max(m,n)). Assume that mm and nn represents the length of l1l1 and l2l2 respectively,
     * the algorithm above iterates at most \max(m, n)max(m,n) times.
     * <p>
     * Space complexity : O(\max(m, n))O(max(m,n)). The length of the new list is at most \max(m,n) + 1max(m,n)+1.
     */


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p = dummy;

        int carry = 0;

        while (p1 != null || p2 != null || carry != 0) {
            int v1 = p1 == null ? 0 : p1.val;
            int v2 = p2 == null ? 0 : p2.val;

            int sum = v1 + v2 + carry;
            carry = sum / 10;
            int v = sum % 10;
            p.next = new ListNode(v);
            p = p.next;

            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }

        return dummy.next;
    }
}
