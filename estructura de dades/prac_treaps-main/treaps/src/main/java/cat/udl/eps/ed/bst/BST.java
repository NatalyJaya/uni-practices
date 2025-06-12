package cat.udl.eps.ed.bst;

import java.util.Objects;

public class BST<Key extends Comparable<? super Key>, Value> implements Map<Key, Value> {

    private Node<Key, Value> root;
    
    private static class Node<Key extends Comparable<? super Key>, Value> {
        Key key;
        Value value;
        Node<Key, Value> left;
        Node<Key, Value> right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(Key key, Value value) {
       // throw new UnsupportedOperationException("Not implemented yet");
        if (key == null) throw new NullPointerException("Key is null");

        root = put(root,key, value);

    }
    // insert the key-value pair in the subtree rooted at h
    private Node<Key, Value> put(Node<Key, Value> h, Key key, Value val) {
        if (h == null) return new Node<>(key, val);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.value = val;
        return h;
    }

    @Override
    public Value get(Key key) {
        if( key == null)  throw new NullPointerException("Key is null");
        return get(root, key);
    }

    private Value get(Node<Key, Value> x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    @Override
    public void remove(Key key) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        root = remove(root, key);
    }

    private Node<Key, Value> remove(Node<Key, Value> node, Key key) {
        if (node == null) {
            return null; // Clau no trobada
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            // Node trobat
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Node amb dos fills: substituir pel successor
            Node<Key, Value> minNode = min(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = remove(node.right, minNode.key);
        }
        return node;
    }
    private Node<Key, Value> min(Node<Key, Value> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean containsKey(Key key) {
       // throw new UnsupportedOperationException("Not implemented yet");
        if(key==null) throw new NullPointerException("Key is null");
        return containsKey(root, key);
    }

    private boolean containsKey(Node<Key, Value> node, Key key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return true; // Clau trobada
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
       // throw new UnsupportedOperationException("Not implemented yet");
        return root == null;
    }

    public int height() {
       // throw new UnsupportedOperationException("Not implemented yet");
        return height(root);
    }
    private int height(Node<Key, Value> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

}
