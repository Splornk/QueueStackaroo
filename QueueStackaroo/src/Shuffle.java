import java.util.Iterator;

public class Shuffle {
    public static void main(String[] args) {
        /*
         * Create "deck"
         */
        QueueStackaroo<Integer> deck = new QueueStackaroo1<Integer>();
        for(int i = 1; i < 53; i++){
            deck.add(i);
        }
        /*
         * Run through deck to see initial organization
         */
        Iterator<Integer> iter = deck.iterator();
        int row = 0;
        while(iter.hasNext()){
            if (row < 12){
                System.out.print(iter.next() + ", ");
                row++;
            } else{
                row = 0;
                System.out.println(iter.next());
            }
        }
        /*
         * Use the randomness of the component to "shuffle" the deck
         */
        deck.flip();
        deck.rotate(51);
        deck.flip();
        /*
         * Run through deck again to see the changes
         */
        Iterator<Integer> iter1 = deck.iterator();
        int row1 = 0;
        while(iter1.hasNext()){
            if (row1 < 12){
                System.out.print(iter1.next() + ", ");
                row1++;
            } else{
                row1 = 0;
                System.out.println(iter1.next());
            }
        }
        /*
         * Continue whatever card game now
         */
    }
}
