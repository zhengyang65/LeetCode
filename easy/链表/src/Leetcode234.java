import java.util.ArrayList;
import java.util.List;

public class Leetcode234 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 方法一：将值复制到数组中后用双指针法。
     * 时间复杂度：O(n)，其中 n 指的是链表的元素个数。
     * 第一步： 遍历链表并将值复制到数组中，O(n)
     * 第二步：双指针判断是否为回文，执行了 O(n/2) 次的判断，即 O(n)。
     * 总的时间复杂度：O(2n) = O(n)
     * 空间复杂度：O(n)，其中 n 指的是链表的元素个数，我们使用了一个数组列表存放链表的元素值。

     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 方法二： 递归
     * 重点掌握递归思路
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 我们要理解计算机如何运行递归函数，在一个函数中调用一个函数时，
     * 计算机需要在进入被调用函数之前跟踪它在当前函数中的位置（以及任何局部变量的值），
     * 通过运行时存放在堆栈中来实现（堆栈帧）。在堆栈中存放好了数据后就可以进入被调用的函数。
     * 在完成被调用函数之后，他会弹出堆栈顶部元素，以恢复在进行函数调用之前所在的函数。
     * 在进行回文检查之前，递归函数将在堆栈中创建 nn 个堆栈帧，计算机会逐个弹出进行处理。
     * 所以在使用递归时空间复杂度要考虑堆栈的使用情况。
     */
    class Solution2 {
        private ListNode frontPointer;

        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) {
                    return false;
                }
                if (currentNode.val != frontPointer.val) {
                    return false;
                }
                frontPointer = frontPointer.next;
            }
            return true;
        }

        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }
    }

    /**
     * 方法三：快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 算法：
     * 整个流程可以分为以下五个步骤：
     * 找到前半部分链表的尾节点。
     * 反转后半部分链表。
     * 判断是否回文。
     * 恢复链表。
     * 返回结果。
     * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
     *
     * 我们可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
     * 当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     */
    class Solution3 {
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }

            // 找到前半部分链表的尾节点并反转后半部分链表
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // 判断是否回文
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) {
                    result = false;
                }
                p1 = p1.next;
                p2 = p2.next;
            }

            // 还原链表并返回结果
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }

}
