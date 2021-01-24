package medium;

import java.util.*;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

public class NestedIterator implements Iterator<Integer> {
    List<Integer> res;
    int count = 0;
    int size;
    public NestedIterator(List<NestedInteger> nestedList) {
        res = new ArrayList<>();
        extract(nestedList);
        size = res.size();
    }

    public void extract(List<NestedInteger> nestedList) {
        for (NestedInteger n:nestedList) {
            if (!n.isInteger()) {
                extract(n.getList());
            } else {
                res.add(n.getInteger());
            }
        }
    }

    @Override
    public Integer next() {
        return res.get(count++);
    }

    @Override
    public boolean hasNext() {
        return count < size;
    }

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new LinkedList<>();
        //nestedList.add(new NestedInteger[][][]{1,{4,{6}}});
        NestedIterator i = new NestedIterator(nestedList);
        //while (i.hasNext()) v[f()] = i.next();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
