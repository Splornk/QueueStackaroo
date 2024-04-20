import java.util.concurrent.ThreadLocalRandom;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class QueueStackarooMVP<T> {
    private Sequence<T> rep;
    private boolean isQueue;
    private int counter;

    private void switchComponent() {
        this.counter--;
        if (this.counter == 0) {
            this.isQueue = !this.isQueue;
            this.counter = ThreadLocalRandom.current().nextInt(1, 30);
            System.out.println("The component has switcherooed!");
        }
    }

    public QueueStackarooMVP() {
        this.rep = new Sequence1L<T>();
        this.isQueue = true;
        this.counter = ThreadLocalRandom.current().nextInt(1, 15);
    }

    public void add(T x) {
        if (this.isQueue) {
            this.rep.add(this.rep.length(), x);
        } else {
            this.rep.add(0, x);
        }
        this.switchComponent();
    }

    public T remove() {
        //Will have requires that makes sure this is not empty
        T remove = this.rep.remove(0);
        this.switchComponent();
        return remove;
    }

    /*
     * Should I allow the component to switch in methods that are not doing
     * anything (like length and isQueue)? By giving it a chance to switch in
     * these the user can repeadtly just call these until it switches back to
     * the one they want, basically giving them control.
     */
    public int length() {
        return this.rep.length();
    }

    public boolean isQueue() {
        return this.isQueue;
    }

    /*
     * Because all enhanced methods will call keneral methods, which also
     * decrease the counter, secondary methods will be far more likely to switch
     * the component. Lesson is, these enhanced methods come with a cost.
     *
     * Won't really show this enhanced method cost in MVP but will show in later
     * parts of the project.
     */
    public T next() {
        //Will have a requires to check that this is not empty
        this.switchComponent();
        return this.rep.entry(0);
    }

    public void flip() {
        Sequence<T> temp = new Sequence1L<T>();
        while (this.rep.length() != 0) {
            temp.add(temp.length(), this.rep.remove(this.rep.length() - 1));
        }
        this.rep.transferFrom(temp);
        this.switchComponent();
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        int menu = 0;
        int num = 0;

        QueueStackarooMVP<Integer> showcase = new QueueStackarooMVP<Integer>();

        //Allows testing for the random switching.
        while (menu != 99) {
            out.print(
                    "Enter which number you would like to do \n1: add\n2: remove\n3: length\n4: isQueue\n5: next\n6: flip\n99: Quit\t");
            menu = in.nextInteger();
            switch (menu) {
                case 1: {
                    System.out.print("\n\n\n");
                    out.print("What number would you like to add:\t");
                    num = in.nextInteger();
                    showcase.add(num);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.print("\n\n\n");
                    break;
                }
                case 2: {
                    System.out.print("\n\n\n");
                    out.print("Removed: " + showcase.remove());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.print("\n\n\n");
                    break;
                }
                case 3: {
                    System.out.print("\n\n\n");
                    out.print("Length: " + showcase.length());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.print("\n\n\n");
                    break;
                }
                case 4: {
                    System.out.print("\n\n\n");
                    out.print("isQueue: " + showcase.length());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.print("\n\n\n");
                    break;
                }
                case 5: {
                    System.out.print("\n\n\n");
                    out.print("Front: " + showcase.next());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.print("\n\n\n");
                    break;
                }
                case 6: {
                    System.out.print("\n\n\n");
                    showcase.flip();
                    out.print("Front after flip: " + showcase.next());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.print("\n\n\n");
                    break;
                }
                default: {
                    System.out.print("\n\n\n");
                    break;
                }
            }
        }
    }
}
