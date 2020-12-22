public class Leetcode237 {
     public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    public static void deleteNode(ListNode node) {
        while (node.next != null & node.next.next !=null) {
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }

    /**
     * 只需要换一个值即可
     * @param node
     */
    public void deleteNod2(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(5);

        deleteNode(node);
    }
}
