package medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leetcode138 {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node ptr = head;
        Node res = new Node(-1);
        Node temp = res;
        Map<Node, Integer> map = new HashMap<>();
        List<Node> list = new LinkedList<>();
        int i = 0;
        while (ptr != null) {
            map.put(ptr, i++);
            temp.next = new Node(ptr.val);
            list.add(temp.next);
            temp = temp.next;
            ptr = ptr.next;
        }
        ptr = head;
        temp = res.next;
        while (ptr != null) {
            if (map.get(ptr.random) != null) {
                temp.random = list.get(map.get(ptr.random));
            }
            ptr = ptr.next;
            temp = temp.next;
        }
        return res.next;
    }

    /**
     * 方法二：回溯
     * 时间复杂度：O(N)，其中 N是链表中节点的数目。
     * 空间复杂度：O(N)
     */
    static HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
    public static Node copyRandomList2(Node head) {

        if (head == null) {
            return null;
        }

        // 如果已经处理了当前节点，则只需返回其克隆版本。
        if (visitedHash.containsKey(head)) {
            return visitedHash.get(head);
        }

        // 创建一个值与旧节点相同的新节点。 （即复制节点）
        Node node = new Node(head.val);

        // 将此值保存在哈希表中。 这是必需的，因为遍历过程中可能由于随机指针的随机性而导致循环，这将有助于我们避免循环。
        visitedHash.put(head, node);

        // 以递归方式复制剩余的链表，从下一个指针开始，然后从随机指针开始。
        // 因此，我们有两个独立的递归调用。
        // 最后，我们为创建的新节点更新下一个和随机指针。
        node.next = copyRandomList2(head.next);
        node.random = copyRandomList2(head.random);

        return node;
    }

    /**
     * 方法三：遍历
     * 时间复杂度：O(N) 。因为我们需要将原链表逐一遍历。
     * 空间复杂度：O(N) 。
     */
    HashMap<Node, Node> visited = new HashMap<Node, Node>();
    public Node getClonedNode(Node node) {
        // If the node exists then
        if (node != null) {
            // Check if the node is in the visited dictionary
            if (this.visited.containsKey(node)) {
                // If its in the visited dictionary then return the new node reference from the dictionary
                return this.visited.get(node);
            } else {
                // Otherwise create a new node, add to the dictionary and return it
                this.visited.put(node, new Node(node.val));
                return this.visited.get(node);
            }
        }
        return null;
    }

    public Node copyRandomList3(Node head) {

        if (head == null) {
            return null;
        }

        Node oldNode = head;

        // Creating the new head node.
        Node newNode = new Node(oldNode.val);
        this.visited.put(oldNode, newNode);

        // Iterate on the linked list until all nodes are cloned.
        while (oldNode != null) {
            // Get the clones of the nodes referenced by random and next pointers.
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            // Move one step ahead in the linked list.
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }


    /**
     * 方法四：通过扭曲原来的链表，并将每个拷贝节点都放在原来对应节点的旁边 ，使得旧节点和新节点交错
     * 时间复杂度：O(N)O(N)
     * 空间复杂度：O(1)O(1)
     * @param head
     * @return
     */
    public Node copyRandomList4(Node head) {
        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    public static void main(String[] args) {
        Node test = new Node(7);
        test.next = new Node(13);
        test.next.next = new Node(11);
        test.next.next.next = new Node(10);
        test.next.next.next.next = new Node(1);
        test.next.random = test;
        test.next.next.random = test.next.next.next.next;
        test.next.next.next.random = test.next.next;
        test.next.next.next.next.random = test;

        Node ans = copyRandomList(test);
        System.out.println(1);
    }
}
