import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leetcode148 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        ListNode ptr = head;
        while (ptr != null) {
            list.add(ptr.val);
            ptr = ptr.next;
        }
        //list.sort((o1, o2) -> o1 - o2);
        list.sort(Comparator.comparingInt(o -> o));
        ListNode ptr2 = head;
        while (ptr2 != null) {
            ptr2.val = list.remove(0);
            ptr2 = ptr2.next;
        }
        return head;
    }

    /**
     * 方法二：自顶向下归并排序
     * 思路：
     * 1.找到链表的中点，以中点为分界，将链表拆分成两个子链表。
     * 寻找链表的中点可以使用快慢指针的做法，快指针每次移动 2步，慢指针每次移动 1步，
     * 当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
     * 2.对两个子链表分别排序。
     * 3.将两个排序后的子链表合并，得到完整的排序后的链表。
     *
     * 时间复杂度：O(nlogn)，其中 n是链表的长度。
     * 空间复杂度：O(logn)，其中 n是链表的长度。空间复杂度主要取决于递归调用的栈空间。
     *
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);  // 妙
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;    // 妙
    }


    /**
     * 方法三：自底向上归并排序
     * 时间复杂度：O(nlogn)，其中 n是链表的长度。
     * 空间复杂度：O(1)。
     * @param head
     * @return
     */
    public static ListNode sortList3(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        //subLength = 1，subLength <<= 1，妙！
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;     //？？
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge3(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(-1);
        node.next = new ListNode(5);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(0);
        ListNode ans = sortList3(node);
        System.out.println(ans);
    }
}
