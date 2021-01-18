import java.util.*;

/**
 * 超出时间限制
 */
public class LRUCache {
    Map<Integer, Integer> map;    //key, index
    //补一个map?
    int capacity;
    List<List<Integer>> list;  //按照index ， 依次key, val
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int index = map.get(key);
            List<Integer> getlist= list.remove(index);
            int result = getlist.get(1);
            update(index);
            list.add(getlist);
            map.put(key, list.size() - 1);
            return result;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            int index = map.get(key);
            list.remove(index);
            update(index);
        } else {
            if (list.size() == capacity) {
                map.remove(list.get(0).get(0));
                list.remove(0);
                update(0);
            }
        }
        list.add(List.of(key, value));
        map.put(key, list.size() - 1);
    }

    public void update(int index) {
        for (int i = index; i < list.size(); i++) {
            map.put(list.get(i).get(0), i);
        }
    }
    public static void main(String[] args) {

        int answer = 0;
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 0); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        answer = lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        answer = lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        answer = lRUCache.get(1);    // 返回 -1 (未找到)
        answer = lRUCache.get(3);    // 返回 3
        answer = lRUCache.get(4);    // 返回 4

    }
}

/**
 * 2.0版本，为超时但性能仍然不佳
 */
class  LRUCache2 {
    Map<Integer, List<Integer>> map;    //key, index
    int capacity;
    List<List<Integer>> list;  //按照index ， 依次key, val

    public LRUCache2(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            List<Integer> getlist = map.get(key);
            list.remove(getlist);
            int result = getlist.get(1);
            list.add(getlist);
            return result;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        List<Integer> putlist = List.of(key, value);
        if (map.containsKey(key)) {
            list.remove(map.get(key));
        } else {
            if (list.size() == capacity) {
                map.remove(list.get(0).get(0));
                list.remove(0);
            }
        }
        list.add(putlist);
        map.put(key, putlist);
    }
}

/**
 * 现成的数据结构：LinkedHashMap
 *
 * 方法二:哈希表 + 双向链表
 * 时间复杂度：对于 put 和 get 都是 O(1)。
 * 空间复杂度：O(capacity)，因为哈希表和双向链表最多存储 capacity + 1 个元素。
 */
class LRUCache3 {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache3(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) { // 双向操作
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) { //双向操作
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
