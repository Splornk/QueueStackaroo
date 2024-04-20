import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing the secondary methods of the QueueStackaroo component.
 */
public class QueueStackarooSecondaryTest {

    /**
     * Testing front with 1 item.
     */
    @Test
    public void frontWithOneItem() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        int front = q.front();
        qCopy.add(1);
        assertEquals(1, front);
        assertEquals(qCopy, q);
    }

    /**
     * Testing front with multiple items.
     */
    @Test
    public void frontWithMultipleItems() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        int front = q.front();
        qCopy.add(1);
        qCopy.add(2);
        assertEquals(1, front);
        assertEquals(qCopy, q);
    }

    /**
     * Testing replaceFront with 1 item.
     */
    @Test
    public void replaceFrontWithOneItem() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        int oldFront = q.replaceFront(2);
        qCopy.add(2);
        assertEquals(1, oldFront);
        assertEquals(qCopy, q);
    }

    /**
     * Testing replaceFront with multiple items.
     */
    @Test
    public void replaceFrontWithMultipleItems() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        int oldFront = q.replaceFront(3);
        qCopy.add(3);
        qCopy.add(2);
        assertEquals(1, oldFront);
        assertEquals(qCopy, q);
    }

    /**
     * Testing append with empty components.
     */
    @Test
    public void appendBothEmpty() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1 = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.append(q1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing append with this empty but not the other.
     */
    @Test
    public void appendThisEmpty() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1 = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1Copy = new QueueStackaroo1<Integer>();
        q1.add(1);
        qCopy.add(1);
        q.append(q1);
        assertEquals(q1Copy, q1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing append with both non-empty in queueMode.
     */
    @Test
    public void appendNonEmptyQueue() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1 = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1Copy = new QueueStackaroo1<Integer>();
        q.add(1);
        q1.add(2);
        qCopy.add(1);
        qCopy.add(2);
        q.append(q1);
        assertEquals(q1Copy, q1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing append with both non-empty not in queueMode.
     */
    @Test
    public void appendNonEmptyStack() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1 = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1Copy = new QueueStackaroo1<Integer>();
        q.add(1);
        while (q.isInQueueMode()) {
            q.add(1);
            q.remove();
        }
        q1.add(2);
        qCopy.add(2);
        qCopy.add(1);
        q.append(q1);
        assertEquals(q1Copy, q1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing flip empty.
     */
    @Test
    public void flipEmpty() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.flip();
        assertEquals(qCopy, q);
    }

    /**
     * Testing flip with 1 item.
     */
    @Test
    public void flipWithOne() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.flip();
        qCopy.add(1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing flip with multiple items.
     */
    @Test
    public void flipMultiple() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        q.flip();
        qCopy.add(2);
        qCopy.add(1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing rotate empty.
     */
    @Test
    public void rotateEmpty() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.rotate(1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing rotate with 1 item.
     */
    @Test
    public void rotateWithOne() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.rotate(1);
        qCopy.add(1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing flip with multiple items.
     */
    @Test
    public void rotateMultiple() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        q.rotate(1);
        qCopy.add(2);
        qCopy.add(1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing gamble. Will fail if data is deleted, but this fail is actually
     * part of the test to measure if the random component works.
     */
    @Test
    public void gambleTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        q.add(1);
        q.gamble();
        assertEquals(false, q.isInQueueMode());
        assertEquals(1, q.length());
    }
}
