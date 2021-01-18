public class Leetcode142 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /** 方法:快慢指针
     *  时间复杂度：O(N)
     *  空间复杂度：O(1)
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean flag = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return null;
        } else {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(3);
        root.next = new ListNode(2);
        root.next.next = new ListNode(0);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = root.next;
        ListNode ans = detectCycle(root);
        System.out.println(ans.val);
    }
}
