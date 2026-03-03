package ru.example.edu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList1<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList1() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(T item) {
        Node newNode = new Node(item, null, last);

        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }

        last = newNode;
        size++;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, first, null);

        if (isEmpty()) {
            last = newNode;
        } else {
            first.prev = newNode;
        }

        first = newNode;
        size++;
    }


    public boolean remove(T item) {
        if (isEmpty()) {
            return false;
        }

        Node curr = first;

        while (curr != null && !curr.data.equals(item)) {
            curr = curr.next;
        }

        if (curr == null) {
            return false;
        }

        unlink(curr);
        return true;
    }


    private void unlink(Node node) {

        if (node.prev == null) {
            first = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next == null) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        size--;
    }

    private class Itr implements Iterator<T> {
        private Node cursor = first;
        private Node lastReturned = null;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = cursor;
            cursor = cursor.next;
            return lastReturned.data;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            unlink(lastReturned);
            lastReturned = null;
        }
    }
}
