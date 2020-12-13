public class Leetcode141 {

     class ListNode {
         int val;
         ListNode next;
         ListNode(int x) {
             val = x;
             next = null;
          }
     }

    /**
     * 快慢指针
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param head
     * @return
     */
     public boolean hasCycle(ListNode head) {
         if (head == null || head.next == null) {
             return false;
         }
         ListNode slow = head;
         ListNode fast = head.next;
         while (slow != fast) {
             if (fast == null || fast.next == null) {
                 return false;
             }
             slow = slow.next;
             fast = fast.next.next;
         }
         return true;
     }

}
