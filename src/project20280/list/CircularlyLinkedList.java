package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        Node<E> curr = tail.getNext();
        for (int k = 0; k < i; k++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node<E> curr = tail.getNext();
            for (int k = 0; k < i; k++) {
                curr = curr.getNext();
            }
            Node<E> newNode = new Node<>(e, curr.getNext());
            curr.setNext(newNode);
            size++;
        }
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        if (i == 0) {
            return removeFirst();
        }
        if (i == size - 1) {
            return removeLast();
        }
        Node<E> pred = tail.getNext();
        for (int k = 0; k < i - 1; k++) {
            pred = pred.getNext();
        }
        E data = pred.getNext().getData();
        pred.setNext(pred.getNext().getNext());
        size--;
        return data;
    }

    public void rotate() {
        if (isEmpty()) {
            return;
        }
        tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator implements Iterator<E> {
        Node<E> curr = tail != null ? tail.getNext() : null;
        int count = 0;

        @Override
        public boolean hasNext() {
            return curr != null && count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E res = curr.getData();
            curr = curr.getNext();
            count++;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            E data = tail.getData();
            tail = null;
            size = 0;
            return data;
        }
        E data = tail.getNext().getData();
        tail.setNext(tail.getNext().getNext());
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        }
        Node<E> pred = tail.getNext();
        while (pred.getNext() != tail) {
            pred = pred.getNext();
        }
        E data = tail.getData();
        pred.setNext(tail.getNext());
        tail = pred;
        size--;
        return data;
    }

    @Override
    public void addFirst(E e) {
        if (isEmpty()) {
            Node<E> newNode = new Node<>(e, null);
            newNode.setNext(newNode);
            tail = newNode;
            size = 1;
        } else {
            Node<E> newNode = new Node<>(e, tail.getNext());
            tail.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addLast(E e) {
        if (isEmpty()) {
            Node<E> newNode = new Node<>(e, null);
            newNode.setNext(newNode);
            tail = newNode;
            size = 1;
        } else {
            Node<E> newNode = new Node<>(e, tail.getNext());
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }


    public String toString() {
        if (tail == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail.getNext();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(curr.getData());
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        try {
            System.out.println("start");
            CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
            for (int i = 10; i < 20; ++i) {
                ll.addLast(i);
            }

            System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

            for (Integer e : ll) {
                System.out.println("value: " + e);
            }
            System.out.println("done");
        } catch (Throwable t) {
            System.err.println("Error: " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }
}
