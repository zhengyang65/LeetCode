public class Leetcode2 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int bias = 0;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + bias;
            bias = 0;
            if (val >= 10) {
                val -= 10;
                bias = 1;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null) {
            while (bias == 1 && l2 != null) {
                int val = l2.val + bias;
                bias = 0;
                if (val >= 10) {
                    val -= 10;
                    bias = 1;
                }
                cur.next = new ListNode(val);
                cur = cur.next;
                l2 = l2.next;
            }
            cur.next = l2;
        } else {
            while (bias == 1 && l1 != null) {
                int val = l1.val + bias;
                bias = 0;
                if (val >= 10) {
                    val -= 10;
                    bias = 1;
                }
                cur.next = new ListNode(val);
                cur = cur.next;
                l1 = l1.next;
            }
            cur.next = l1;
        }
        if (bias == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }

    /**
     * 思路：如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 0.
     *
     * 时间复杂度：O(max(m,n))，其中 m,n为两个链表的长度。我们要遍历两个链表的全部位置，
     * 而处理每个位置只需要 O(1)的时间。
     * 空间复杂度：O(max(m,n))。答案链表的长度最多为较长链表的长度 +1。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode p = new ListNode(2);
        p.next = new ListNode(4);
        p.next.next = new ListNode(5);
        p.next.next.next = new ListNode(2);
        p.next.next.next.next = new ListNode(1);
        ListNode q = new ListNode(5);
        q.next = new ListNode(6);
        q.next.next = new ListNode(4);
        ListNode ans = addTwoNumbers(p,q);
        System.out.println(1);
    }
}
