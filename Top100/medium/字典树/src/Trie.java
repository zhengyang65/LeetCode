import java.io.PrintStream;

public class Trie {
    public Node root;
    public class Node{
        char c;
        Node left, mid ,right;
        boolean bool = false;
    }
    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root = insert(root, word, 0);
    }
    public Node insert(Node x, String word, int d) {
        char c = word.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {x.left = insert(x.left, word, d);}
        else if (c > x.c) {x.right = insert(x.right, word, d);}
        else if (d < word.length() - 1) {x.mid = insert(x.mid, word, d + 1);}
        else {x.bool = true;}
        return x;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null) {return false;}
        return search(root, word, 0);
    }
    public boolean search(Node x,String word, int d) {
        if (x == null) {return false;}
        char c = word.charAt(d);
        if (c < x.c) {return search(x.left, word, d);}
        else if (c > x.c) {return search(x.right, word, d);}
        else if (d < word.length() - 1) {return search(x.mid, word, d + 1);}
        else {return x.bool;}
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null) {return false;}
        return startsWith(root, prefix, 0);
    }

    public boolean startsWith(Node x, String prefix, int d) {
        if (x == null) {return false;}
        char c = prefix.charAt(d);
        if (c < x.c) {return startsWith(x.left, prefix, d);}
        else if (c > x.c) {return startsWith(x.right, prefix, d);}
        else if (d < prefix.length() - 1) {return startsWith(x.mid, prefix, d + 1);}
        else {return true;}

    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("app");
        obj.insert("apple");
        obj.insert("bear");
        obj.insert("add");
        obj.insert("jam");
        obj.insert("rental");
        boolean ans1 = obj.search("apps");
        boolean ans2 = obj.search("app");
        boolean ans3 = obj.search("ad");
        boolean ans4 = obj.search("applepie");
        boolean ans5 = obj.search("rest");
        boolean ans6 = obj.search("jan");
        boolean ans7 = obj.search("rent");
        boolean ans8 = obj.search("bear");
        boolean ans9 = obj.search("jam");
        boolean ans10 = obj.startsWith("apps");
        boolean ans11 = obj.startsWith("app");
        boolean ans12 = obj.startsWith("ad");
        boolean ans13 = obj.startsWith("applepie");
        boolean ans14 = obj.startsWith("rest");
        boolean ans15 = obj.startsWith("jan");
        boolean ans16 = obj.startsWith("rent");
        boolean ans17 = obj.startsWith("beer");
        boolean ans18 = obj.startsWith("jam");
        System.out.println(1);
    }
}
