import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {

    static LinkedQueue<String> q;

    @BeforeEach
    void setUp() {
        q = new LinkedQueue<>();
    }

    @Test
    void TestQueue() {
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertThrows(NoSuchElementException.class, () -> q.dequeue());

        q.enqueue("1");
        assertEquals(1, q.size());
        assertFalse(q.isEmpty());

        q.enqueue("3");
        assertEquals(2, q.size());

        q.enqueue("2");
        assertEquals(3, q.size());

        assertEquals("1", q.dequeue());
        assertEquals(2, q.size());

        assertEquals("3", q.dequeue());
        assertEquals(1, q.size());

        assertEquals("2", q.dequeue());
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
        assertThrows(NoSuchElementException.class, () -> q.dequeue());
    }
}