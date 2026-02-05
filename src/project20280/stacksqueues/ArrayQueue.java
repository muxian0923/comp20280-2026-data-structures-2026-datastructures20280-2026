package project20280.stacksqueues;

import project20280.interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int front = 0;
    private int sz = 0;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayQueue() {
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
    public void enqueue(E e) {
        if (sz == data.length)
            throw new IllegalStateException("Queue is full");
        data[(front + sz) % data.length] = e;
        sz++;
    }

    @Override
    public E first() {
        return isEmpty() ? null : data[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            return null;
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        sz--;
        return e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < sz; ++i) {
            E res = data[(front + i) % data.length];
            sb.append(res);
            if (i != sz - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> qq = new ArrayQueue<>();
        System.out.println(qq);

        int N = 10;
        for (int i = 0; i < N; ++i) {
            qq.enqueue(i);
        }
        System.out.println(qq);

        for (int i = 0; i < N / 2; ++i) qq.dequeue();
        System.out.println(qq);

    }
}
