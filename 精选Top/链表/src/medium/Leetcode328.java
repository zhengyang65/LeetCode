package medium;

public class Leetcode328 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode even = head;
        int size = 0;
        while (even.next != null) {
            even = even.next;
            size++;
        }
        ListNode odd = head;
        int count = 0;
        while (odd.next != null && odd.next.next != null && count < size/2) {
            even.next = odd.next;
            odd.next = odd.next.next;
            even.next.next = null;
            even = even.next;
            odd = odd.next;
            count++;
        }
        if (size % 2 == 1) {
            even.next = odd.next;
            odd.next = odd.next.next;
            even.next.next = null;
        }
        return head;
    }

    /**
     * 官方解：分离节点后合并，更简洁
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    public static void main(String[] args) {
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);
        //test.next.next.next.next = new ListNode(5);

        ListNode ans = oddEvenList(test);
        System.out.println(1);
    }
}
