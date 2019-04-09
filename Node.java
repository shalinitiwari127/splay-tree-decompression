package com.example.vision.myapplication;


public class Node<E> implements Comparable<Node<E>> {

    private E data;
    private int count;
    private Node<E> left;
    private Node<E> right;

    /**
     * Constructs a new node with the given data, count, and references to the given left and right nodes.
     */
    public Node(E data, int count,Node<E> left, Node<E> right) {
        this.data = data;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructs a new node containing the given data and count. Its left and right references will be set to null.
     */
    public Node(E data, int count) {
        this(data, count, null, null);
    }

    /**
     * Constructs a new internal node with the given two children. The new node's data will be null and its count will be
     * the sum of that in its two children.
     */
    public Node(Node<E> left, Node<E> right) {
        this(null, 0, left, right);
        count += (this.getLeft() == null) ? 0 : this.getLeft().getCount();
        count += (this.getRight() == null) ? 0 : this.getRight().getCount();
    }

    /**
     * Constructs a new node with no data, a count of 0, and no children. Since data is null, this will be an internal
     * node.
     */
    public Node() {
        this(null, 0, null, null);
    }

    /** Returns the count stored in this node. */
    public int getCount() {
        return count;
    }

    /** Updates the count stored in this node. */
    public void setCount(int count) {
        this.count = count;
    }

    /** Returns the item currently stored in this node. May be null. */
    public E getData() {
        return data;
    }

    /** Overwrites the item stored in this Node with the given data item. */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns this Node's left child. If there is no left left, returns null.
     */
    public Node<E> getLeft() {
        return left;
    }

    /** Causes this Node to point to the given left child Node. */
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    /**
     * Returns this nodes right child. If there is no right child, returns null.
     */
    public Node<E> getRight() {
        return right;
    }

    /** Causes this Node to point to the given right child Node. */
    public void setRight(Node<E> right) {
        this.right = right;
    }

    /** True if both of this node's children are null. */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(Node<E> other) {
        // smaller counts before larger
        return this.count - other.count;
    }

    /**
     * Returns this node's value (if any; "*" if not) and its count in parentheses. If E is Byte, will also include a
     * 'char' view of any printable byte value.
     */
    @Override
    public String toString() {
        String str = "";
        if (this.data == null) {
            str += "*";
        }
        else {
            // in standard printable ASCII range
            str += this.data;
            if (this.data instanceof Byte) {
                byte b = (Byte) this.data;
                if (b >= 32 && b < 127) {
                    str += " '" + (char) b + "'";
                }
                else if (b == 9) {
                    str += " 'TAB'"; // tab
                }
                else if (b == 10) {
                    str += " 'LF'"; // line feed (\n)
                }
                else if (b == 13) {
                    str += " 'CR'"; // carriage return (\r)
                }
            }
        }
        str += " (x " + this.count + ")";
        return str;
    }


    public String toFullString(String prefix) {
        String str = prefix + this.toString() + "\n";
        if (this.getLeft() != null) {
            str += this.getLeft().toFullString(prefix + "<");
        }
        if (this.getRight() != null) {
            str += this.getRight().toFullString(prefix + ">");
        }
        return str;
    }
}


