public class Leetcode21 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result;
        if (l1 == null & l2 == null) {
            return null;
        }
        if (l1 != null & l2 == null) {
            return l1;
        }
        if (l1 == null & l2 != null) {
            return l2;
        }
        if (l1.val < l2.val) {
            result = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            result = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode curr = result;
        while(l1 != null | l2 != null) {
            if (l1 == null) {
                ListNode p = new ListNode(l2.val);
                curr.next = p;
                curr = curr.next;
                l2 = l2.next;
                continue;
            }
            if (l2 == null) {
                ListNode p = new ListNode(l1.val);
                curr.next = p;
                curr = curr.next;
                l1 = l1.next;
                continue;
            }
            if (l1.val < l2.val) {
                ListNode p = new ListNode(l1.val);
                curr.next = p;
                curr = curr.next;
                l1 = l1.next;
            } else {
                ListNode p = new ListNode(l2.val);
                curr.next = p;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        return result;
    }

    /**
     * 方法一：精妙的递归
     * 时间复杂度：O(n + m) ，其中 n 和 m 分别为两个链表的长度。
     * 空间复杂度：O(n + m)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }


    /**
     *方法一：更好的迭代
     *时间复杂度：O(n + m) ，其中 n 和 m 分别为两个链表的长度。
     *空间复杂度：O(1)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
