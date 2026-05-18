package ua.khpi.oop.lab10.container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TaskContainer<T> implements Iterable<T> {

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public TaskContainer() {
        data = (T[]) new Object[10];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public TaskContainer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void add(T item) {
        if (size == data.length) {
            grow();
        }

        data[size] = item;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    public T remove(int index) {
        checkIndex(index);

        T removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return removed;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        T[] newData = (T[]) new Object[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index: " + index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TaskIterator();
    }

    private class TaskIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more tasks");
            }

            T item = data[current];
            current++;

            return item;
        }
    }
}