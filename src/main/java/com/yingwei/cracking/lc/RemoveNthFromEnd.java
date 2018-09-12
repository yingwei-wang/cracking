package com.yingwei.cracking.lc;

public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p1=dummy, p2=dummy;

        while (p1.next != null && n > 0) {
            p1 = p1.next;
            n--;
        }

        if (n != 0) return head;

        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;

        return dummy.next;
    }
}
