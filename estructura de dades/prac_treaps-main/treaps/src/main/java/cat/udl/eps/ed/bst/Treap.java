package cat.udl.eps.ed.bst;

import java.util.Objects;

public class Treap<Key extends Comparable<? super Key>, Value> implements Map<Key, Value> {

    private Node<Key, Value> root;

    private static class Node<Key extends Comparable<? super Key>, Value> {
        Key key;
        Value value;
        double priority;
        Node<Key, Value> left;
        Node<Key, Value> right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.priority = Math.random();
        }
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new NullPointerException("Key is null");
        root = put(root, key, value);
    }

    private Node<Key, Value> put(Node<Key, Value> h, Key key, Value value) {
        if (h == null) return new Node<>(key, value);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
            if (h.left.priority < h.priority) {
                h = rotateRight(h);
            }
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
            if (h.right.priority < h.priority) {
                h = rotateLeft(h);
            }
        } else {
            h.value = value;
        }

        return h;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("Key is null");
        Node<Key, Value> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null; // Si la clau no existeix
    }

    private Node<Key, Value> rotateRight(Node<Key, Value> h) {
        Node<Key, Value> x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node<Key, Value> rotateLeft(Node<Key, Value> h) {
        Node<Key, Value> x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
    @Override
    public void remove(Key key) {
        if (key == null) throw new NullPointerException("Key is null");
        root = remove(root, key);
    }

    private Node<Key, Value> remove(Node<Key, Value> h, Key key) {
        if (h == null) return null;

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = remove(h.left, key);
        } else if (cmp > 0) {
            h.right = remove(h.right, key);
        } else {

            if (h.left == null) return h.right;
            if (h.right == null) return h.left;

            if (h.left.priority < h.right.priority) {
                h = rotateRight(h);
                h.right = remove(h.right, key);
            } else {
                h = rotateLeft(h);
                h.left = remove(h.left, key);
            }
        }
        return h;
    }

    @Override
    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<Key, Value> h) {
        if (h == null) return 0;
        return 1 + Math.max(height(h.left), height(h.right));
    }
}
