package project20280.stacksqueues;

import project20280.interfaces.Deque;

/**
 * Realization of a double-ended queue by means of a circular array
 * (see Data Structures and Algorithms in Java, 6th ed., Goodrich et al.).
 */
public class ArrayDeque<E> implements Deque<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int front = 0;   // index of the first element
    private int sz = 0;

    @SuppressWarnings("unchecked")
    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayDeque() {
        this(CAPACITY);
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public E first() {
        if (isEmpty())
            return null;
        return data[front];
    }

    @Override
    public E last() {
        if (isEmpty())
            return null;
        return data[(front + sz - 1) % data.length];
    }

    @Override
    public void addFirst(E e) {
        if (sz == data.length)
            throw new IllegalStateException("Deque is full");
        front = (front - 1 + data.length) % data.length;
        data[front] = e;
        sz++;
    }

    @Override
    public void addLast(E e) {
        if (sz == data.length)
            throw new IllegalStateException("Deque is full");
        data[(front + sz) % data.length] = e;
        sz++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty())
            return null;
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        sz--;
        return e;
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;
        int lastIdx = (front + sz - 1) % data.length;
        E e = data[lastIdx];
        data[lastIdx] = null;
        sz--;
        return e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < sz; ++i) {
            sb.append(data[(front + i) % data.length]);
            if (i != sz - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
