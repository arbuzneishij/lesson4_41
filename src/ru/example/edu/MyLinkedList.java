package ru.example.edu;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public MyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void add(T item) {
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(item, null);
            prev.next = last;
        } else {
            last = new Node(item, null);
            first = last;
        }
    }

    public void addFirst(T item) {
        if (!isEmpty()) {
            Node next = first;
            first = new Node(item, null);
            first.next = next;
        } else {
            first = new Node(item, null);
            last = first;
        }
    }

    public boolean remove(T item) {
        if (isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot remove() from an empty list.");
        }
        boolean result = false;
        Node prev = null;
        Node curr = first;
        // ищем элемент
        while (curr.next != null && !curr.data.equals(item)) {
            prev = curr;
            curr = prev.next;
        }
        // если нашли - удаляем
        if (curr.data.equals(item)) {
            if (first == last) {
                first = null;
            } else if (curr == first) {
                first = first.next;
            } else if (curr == last) {
                last = prev;
                last.next = null;
            } else {
                prev.next = curr.next;
            }

            result = true;

        }
        return result;
    }
    private class Itr implements Iterator<T> {
        private Node cursor = first; // 4 usages

        @Override
        public T next() {
            T item = cursor.data;
            cursor = cursor.next;
            return item;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public void remove() {
        }
    }
}