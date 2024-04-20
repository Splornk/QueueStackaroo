/**
 * QueueStackaroo Enhanced Methods.
 *
 * @param <T>
 *            The type stored in {@code this}
 */
public interface QueueStackaroo<T> extends QueueStackarooKernel<T> {
    /**
     * Reports the front of this. If this.queueMode switches the data will be
     * mixed up.
     *
     * @aliases reference returned by front
     * @return the front entry of this
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T front();

    /**
     * Attempts to replace the front of this with x, and returns the old front.
     * Guanteed to work if this is not in queue mode. If this is in queue mode x
     * may end up somewhere in the middle.
     *
     * @param x
     *            - the new front entry
     * @aliases reference x
     * @updates {@code this}
     * @return the old front entry
     * @requires this /= <>
     * @ensures <pre>
     * <replaceFront> is prefix of #this and
     * if this is not in queue mode
     *      this = <x> * #this[1, |#this|)
     * else
     *      this is perms(<x> * this[1, |#this|)
     * </pre>
     */
    T replaceFront(T x);

    /**
     * Concatenates ("appends") entries in q to the end of this if in queueMode,
     * else concatenates ("appends") entries in q to the top of this.
     *
     * @param q
     *            - the Queue to be appended to the end of this
     * @updates {@code this}
     * @clears q
     * @ensures this is perms(#this * #q, this * q)
     */
    void append(QueueStackaroo<T> q);

    /**
     * Reverses ("flips") {@code this}. If this.queueMode switches the data will
     * be mixed up.
     *
     * @updates {@code this}
     * @ensures this is perms(rev(#this))
     */
    void flip();

    /**
     * Rotates {@code this}. If this.queueMode switches the data will be mixed
     * up.
     *
     * @param distance
     *            distance by which to rotate
     * @updates {@code this}
     * @ensures <pre>
     * if #this = <> then
     *  this = #this
     * else
     *  this is perms(#this[distance mod |#this|, |#this|)
     *  * #this[0, distance mod |#this|))
     * }
     * </pre>
     */
    void rotate(int distance);

    /**
     * Switches this.queue_mode, however there is a 1/3 chance that all of your
     * data is lost.
     *
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * this.queue_mode = !this.queue_mode but
     * 1/3 chance this.length = |{}|
     * </pre>
     */
    void gamble();
}
