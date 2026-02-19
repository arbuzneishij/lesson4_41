package ru.example.edu;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAP = 5;

    public MyArrayList() {
        this(DEFAULT_CAP);
    }

    public MyArrayList(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void add(E e) {
        if (size == data.length) {
            grow();
        }

        data[size] = e;
        size++;
    }

    private void grow() {
        int prevCap = data.length;
        int newCap = prevCap * 2;

        Object[] tmpData = new Object[newCap];
        System.arraycopy(data, 0, tmpData, 0, prevCap);
        data = tmpData;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + " выходит за пределы");
        }
        return (E) data[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + " выходит за пределы");
        }

        E removedValue = (E) data[index];
        int numberMove = size - index - 1;
        if (numberMove > 0) {
            System.arraycopy(data, index + 1, data, index, numberMove);
        }
        size--;
        data[size] = null;

        return  removedValue;
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;

        @Override
        public boolean hasNext() {
            if (cursor < size)
                return true;
            else
                return false;
        }

        @Override
        public E next() {
            cursor++;
            return (E) data[cursor - 1];
        }
    }
}