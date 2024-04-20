import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import components.queue.Queue;
import components.queue.Queue1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * QueueStackaroo Abstract Class.
 *
 * @param <T>
 *            The type stored in {@code this}
 */
public abstract class QueueStackarooSecondary<T> implements QueueStackaroo<T> {

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof QueueStackaroo<?>)) {
            return false;
        }
        QueueStackaroo<?> q = (QueueStackaroo<?>) o;
        if (this.length() != q.length()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = q.iterator();
        while (it1.hasNext()) {
            T x1 = it1.next();
            Object x2 = it2.next();
            if (!x1.equals(x2)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int hashCode;
        /*
         * Get the hash code of the first object
         */
        if (this.length() >= 1) {
            hashCode = this.front().hashCode();
            /*
             * If empty make hash code 0
             */
        } else {
            hashCode = 0;
        }
        return hashCode;
    }

    @Override
    public final String toString() {
        String contents = "";
        Iterator<T> it1 = this.iterator();
        while (it1.hasNext()) {
            contents += it1.next().toString();
            if (it1.hasNext()) {
                contents += ", ";
            }
        }
        return contents;
    }

    @Override
    public final T front() {
        assert this.length() != 0 : "Violation of: this is not empty";

        T front = this.remove();

        if (this.isInQueueMode()) {
            QueueStackaroo<T> temp = this.newInstance();
            temp.add(front);
            temp.append(this);
            this.transferFrom(temp);
        } else {
            this.add(front);
        }

        return front;
    }

    @Override
    public final T replaceFront(T x) {
        assert this.length() != 0 : "Violation of: this is not empty";

        T oldFront = this.remove();

        if (this.isInQueueMode()) {
            QueueStackaroo<T> temp = this.newInstance();
            temp.add(x);
            temp.append(this);
            this.transferFrom(temp);
        } else {
            this.add(x);
        }

        return oldFront;
    }

    @Override
    public final void append(QueueStackaroo<T> q) {

        while (q.length() != 0) {
            this.add(q.remove());
        }
    }

    @Override
    public final void flip() {
        /*
         * Flip for a queue
         */
        if (this.isInQueueMode()) {
            Stack<T> tempStack = new Stack1L<T>();

            /*
             * Put everything from this into a stack
             */
            while (this.length() != 0) {
                tempStack.push(this.remove());
            }
            /*
             * Put everything from that stack and put it back into this
             */
            while (tempStack.length() != 0) {
                this.add(tempStack.pop());
            }
            /*
             * Flip for a stack
             */
        } else {
            Queue<T> tempQueue = new Queue1L<T>();

            /*
             * Put everything from this into a queue
             */
            while (this.length() != 0) {
                tempQueue.enqueue(this.remove());
            }
            /*
             * Put everything from that queue and put it back into this
             */
            while (tempQueue.length() != 0) {
                this.add(tempQueue.dequeue());
            }
        }
    }

    @Override
    public final void rotate(int distance) {

        Queue<T> tempQueue = new Queue1L<T>();

        /*
         * Put everything from this into a queue
         */
        while (this.length() != 0) {
            tempQueue.enqueue(this.remove());
        }

        /*
         * rotate that queue
         */
        tempQueue.rotate(distance);

        /*
         * Put everything from that queue and put it back into this
         */
        while (tempQueue.length() != 0) {
            this.add(tempQueue.dequeue());
        }
    }

    @Override
    public final void gamble() {
        /*
         * Gets initial mode
         */
        boolean mode = this.isInQueueMode();
        /*
         * Switches modes
         */
        while (this.isInQueueMode() == mode) {
            this.front();
        }

        /*
         * Finds out if all data is lost
         */
        if (ThreadLocalRandom.current().nextInt(1, 4) == 1) {
            while (this.length() != 0) {
                this.remove();
            }
        }
    }
}
