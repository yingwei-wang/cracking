package com.yingwei.cracking.lc;

        import java.util.Comparator;
        import java.util.List;
        import java.util.PriorityQueue;

public class MergeKSortedList {

    public ListNode mergeKLists(List<ListNode> lists) {
        ListNode headRef = new ListNode(0);
        ListNode p = headRef;


        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(10, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            p.next = min;
            p = p.next;
            if (min.next != null) {
                pq.offer(min.next);
            }
        }

        return headRef.next;
    }
}
