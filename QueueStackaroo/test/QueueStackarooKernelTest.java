import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing the kernel methods of the QueueStackaroo component.
 */
public class QueueStackarooKernelTest {

    /**
     * Testing the constructor.
     */
    @Test
    public void constructorTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        assertEquals(true, q.isInQueueMode());
        assertEquals(0, q.length());
    }

    /**
     * Testing newInstance.
     */
    @Test
    public void newInstanceTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> instance = q.newInstance();
        assertEquals(q, instance);
        assertEquals(qCopy, q);
    }

    /**
     * Testing clear when empty.
     */
    @Test
    public void emptyClearTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.clear();
        assertEquals(qCopy, q);
    }

    /**
     * Testing clear when non-empty.
     */
    @Test
    public void nonEmptyClearTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.clear();
        assertEquals(qCopy, q);
    }

    /**
     * Testing transferFrom when empty.
     */
    @Test
    public void emptyTransferFromTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1 = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.transferFrom(q1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing transferFrom when non-empty.
     */
    @Test
    public void nonEmptyTransferFromTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> q1 = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q1.add(1);
        qCopy.add(1);
        q.transferFrom(q1);
        assertEquals(qCopy, q);
    }

    /**
     * Testing add in queue mode.
     */
    @Test
    public void addInQueue() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        int front = q.remove();
        qCopy.add(1);
        assertEquals(1, front);
        assertEquals(qCopy, q);
    }

    /**
     * Testing add in stack mode.
     */
    @Test
    public void addInStack() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();

        while (q.isInQueueMode()) {
            q.add(1);
            q.remove();
        }
        while (qCopy.isInQueueMode()) {
            qCopy.add(1);
            qCopy.remove();
        }

        q.add(1);
        q.add(2);
        int front = q.remove();
        qCopy.add(1);
        qCopy.add(2);
        qCopy.remove();
        assertEquals(2, front);
        assertEquals(qCopy, q);
    }

    /**
     * Testing remove.
     */
    @Test
    public void removeInQueue() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        int front1 = q.remove();
        int front2 = q.remove();
        assertEquals(1, front1);
        assertEquals(2, front2);
        assertEquals(qCopy, q);
    }

    /**
     * Testing length when empty.
     */
    @Test
    public void emptyLengthTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        q.add(1);
        q.add(2);
        qCopy.add(1);
        qCopy.add(2);
        assertEquals(2, q.length());
        assertEquals(qCopy, q);
    }

    /**
     * Testing length when non-empty.
     */
    @Test
    public void nonEmptyLengthTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();
        assertEquals(0, q.length());
        assertEquals(qCopy, q);
    }

    /**
     * Testing isInQueueMode.
     */
    @Test
    public void isInQueueModeTest() {
        QueueStackaroo<Integer> q = new QueueStackaroo1<Integer>();
        QueueStackaroo<Integer> qCopy = new QueueStackaroo1<Integer>();

        while (q.isInQueueMode()) {
            q.add(1);
            q.remove();
            if (!q.isInQueueMode()) {
                System.out.println("Has Switched");
            }
        }
        while (qCopy.isInQueueMode()) {
            qCopy.add(1);
            qCopy.remove();
        }

        assertEquals(qCopy, q);
    }
}
