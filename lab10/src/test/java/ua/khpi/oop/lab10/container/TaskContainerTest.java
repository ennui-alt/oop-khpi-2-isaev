package ua.khpi.oop.lab10.container;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TaskContainerTest {

    @Test
    void newContainerShouldBeEmpty() {
        TaskContainer<String> container = new TaskContainer<>();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
        assertEquals(10, container.capacity());
    }

    @Test
    void containerWithCustomCapacityShouldBeCreated() {
        TaskContainer<Integer> container = new TaskContainer<>(3);

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
        assertEquals(3, container.capacity());
    }

    @Test
    void wrongCapacityShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskContainer<>(0));
        assertThrows(IllegalArgumentException.class, () -> new TaskContainer<>(-2));
    }

    @Test
    void addShouldIncreaseSize() {
        TaskContainer<String> container = new TaskContainer<>();

        container.add("First");
        container.add("Second");

        assertFalse(container.isEmpty());
        assertEquals(2, container.size());
        assertEquals("First", container.get(0));
        assertEquals("Second", container.get(1));
    }

    @Test
    void containerShouldGrowWhenItIsFull() {
        TaskContainer<Integer> container = new TaskContainer<>(2);

        container.add(10);
        container.add(20);
        container.add(30);

        assertEquals(3, container.size());
        assertEquals(4, container.capacity());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
    }

    @Test
    void getWithWrongIndexShouldThrowException() {
        TaskContainer<String> container = new TaskContainer<>();

        container.add("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    void removeShouldDeleteElementAndShiftOtherElements() {
        TaskContainer<String> container = new TaskContainer<>();

        container.add("One");
        container.add("Two");
        container.add("Three");

        String removed = container.remove(1);

        assertEquals("Two", removed);
        assertEquals(2, container.size());
        assertEquals("One", container.get(0));
        assertEquals("Three", container.get(1));
    }

    @Test
    void removeWithWrongIndexShouldThrowException() {
        TaskContainer<String> container = new TaskContainer<>();

        container.add("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    @Test
    void iteratorShouldGoThroughAllElements() {
        TaskContainer<String> container = new TaskContainer<>();

        container.add("A");
        container.add("B");
        container.add("C");

        Iterator<String> iterator = container.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorShouldThrowExceptionWhenNoElementsLeft() {
        TaskContainer<String> container = new TaskContainer<>();

        container.add("A");

        Iterator<String> iterator = container.iterator();

        assertEquals("A", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void forEachShouldWorkCorrectly() {
        TaskContainer<Integer> container = new TaskContainer<>();

        container.add(1);
        container.add(2);
        container.add(3);

        int sum = 0;

        for (Integer number : container) {
            sum += number;
        }

        assertEquals(6, sum);
    }
}