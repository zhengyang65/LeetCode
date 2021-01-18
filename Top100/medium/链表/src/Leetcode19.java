import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Leetcode19 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode ptr = head;
        int size = 0;
        while (ptr != null) {
            stack.push(ptr);
            ptr = ptr.next;
            size += 1;
        }
        if (n == size) {
            return head.next;
        } else if (n == 1) {
            stack.pop();
            ListNode listNode = stack.pop();
            listNode.next = null;
            return head;
        } else {
            while (n > 2) {
                stack.pop();
                n--;
            }
            ptr = stack.pop();
            stack.pop();
            ListNode ptr2 = stack.pop();
            ptr2.next = ptr;
            return head;
        }
    }

    /**
     * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的next指针指向链表的头节点。
     * 时间复杂度：O(L)，其中 LL 是链表的长度。
     * 空间复杂度：O(1)
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //哑节点
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;  //返回哑节点.next
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    /**
     * 同样是用栈
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>(); //用Deque 和 LinkedList
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();  // 不需要再多一个ListNode ptr = prev.next.next
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * 一次遍历（妙！）
     * 时间复杂度：O(L)，其中 LL 是链表的长度。
     * 空间复杂度：O(1)。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        ListNode ans = removeNthFromEnd(root, 4);
        System.out.println(1);
    }
}
