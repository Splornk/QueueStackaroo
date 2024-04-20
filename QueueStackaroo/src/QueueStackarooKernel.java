import components.standard.Standard;

/**
 * First-in-first-out (FIFO) or First-in-last-out (FILO) keneral component with
 * primary methods. Combines the utility of a queue and stack, however you can
 * only use one of them at a time and, you cannot choose which utility is being
 * used. Just by using the component it will randomly switch between the two.
 * (Note: by package-wide convention, all references are non-null.)
 *
 * @param <T>
 *            The type being stored in QueueStackaroo
 */
public interface QueueStackarooKernel<T>
        extends Standard<QueueStackaroo<T>>, Iterable<T> {

    /**
     * Adds {@code x} either to the front or end of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @updates {@code this}
     * @ensures <pre>
     * if this.queue_mode
     *      this = #this * <x>
     * else
     *      this = <x> * #this
     * </pre>
     */
    void add(T x);

    /**
     * Removes the front from {@code this}.
     *
     * @updates {@code this}
     * @return the entry removed
     * @requires this /= <>
     * @ensures #{@code this} = <remove> * {@code this}
     */
    T remove();

    /**
     * Reports the length of {@code this} with no possibility of changing
     * between a queue and a stack.
     *
     * @return the length of {@code this}
     * @ensures length = |{@code this}| and this cannot switch utilities
     */
    int length();

    /**
     * Reports whether {@code this} is in queue mode with no possibility of
     * changing between a queue and a stack.
     *
     * @return true iff {@code this} is in queue mode
     * @ensures isInQueueMode = this.queue_mode and this cannot switch utilities
     */
    boolean isInQueueMode();
}
