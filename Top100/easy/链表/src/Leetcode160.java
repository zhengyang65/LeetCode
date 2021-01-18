import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode160 {

     static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 方法一：哈希表
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != null) {
            set.add(p1);
            p1 = p1.next;
        }
        while (p2 != null) {
            if (!set.add(p2)) {
                return p2;
            }
            p2 = p2.next;
        }
        return null;
    }

    /**
     * 方法二：双指针， 妙！！
     * 当 pA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B);
     * 类似的，当 pB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     *
     * 时间复杂度 : O(m+n)
     * 空间复杂度 : O(1)
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while(p != q){
            p = (p == null) ? headB : p.next;
            q = (q == null) ? headA : q.next;
        }
        return p;
    }
}
