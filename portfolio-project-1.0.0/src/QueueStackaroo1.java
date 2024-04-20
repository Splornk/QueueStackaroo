import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code QueueStackaroo} represented as a {@link components.sequence.Sequence
 * components.sequence.Sequence}, with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code QueueStackaroo} entries
 * @correspondence <pre>
 * this = ($this.insertionMode, $this.machineOrder, $this.rep)
 * </pre>
 */
public class QueueStackaroo1<T> extends QueueStackarooSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * entries of {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Queue mode.
     */
    private boolean queueMode;

    /**
     * Countdown timer for switching.
     */
    private int countdown;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
        this.queueMode = true;
        this.countdown = ThreadLocalRandom.current().nextInt(1, 15);
    }

    /**
     * Used to keep track and switch the component
     */
    private void switchUtility() {
        this.countdown--;
        if (this.countdown == 0) {
            this.queueMode = !this.queueMode;
            this.countdown = ThreadLocalRandom.current().nextInt(1, 30);
            System.out.println("The component has switcherooed!");
        }
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public QueueStackaroo1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */
    @SuppressWarnings("unchecked")
    @Override
    public final QueueStackaroo1<T> newInstance() {
        try {
            this.switchUtility();
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(QueueStackaroo<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof QueueStackaroo1<?> : ""
                + "Violation of: source is of dynamic type WaitingLine1<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Map2<?,?>, and
         * the ?,? must be K,V or the call would not have compiled.
         */
        QueueStackaroo1<T> localSource = (QueueStackaroo1<T>) source;
        this.entries = localSource.entries;
        this.countdown = localSource.countdown;
        this.queueMode = localSource.queueMode;
        localSource.createNewRep();
        this.switchUtility();
    }

    /*
     * Public kernel methods
     * ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        if (this.queueMode) {
            this.entries.add(this.entries.length(), x);
        } else {
            this.entries.add(0, x);
        }
        this.switchUtility();
    }

    @Override
    public final T remove() {
        assert this.entries.length() != 0 : "Violation of: this is not empty";

        T remove = this.entries.remove(0);
        this.switchUtility();
        return remove;
    }

    @Override
    public final int length() {
        return this.entries.length();
    }

    @Override
    public final boolean isInQueueMode() {
        return this.queueMode;
    }

    @Override
    public final Iterator<T> iterator() {
        this.switchUtility();
        return this.entries.iterator();
    }
}
