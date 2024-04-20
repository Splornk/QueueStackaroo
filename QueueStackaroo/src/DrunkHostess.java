public class DrunkHostess {
    public static void main(String[] args) {
        QueueStackaroo<String> customers = new QueueStackaroo1<String>();
        /*
         * The restaurant got busy so the hostess is adding to the end of the line
         */
        customers.add("Sally");
        customers.add("George");
        System.out.println("Okay folks the next person in line is " + customers.front());
        customers.add("Bob");
        /*
             * Used to check if our hostess has made a drunken mistake and
             * has accedently started adding people at the beginning
             */
        if(!customers.isInQueueMode()){
            System.out.println("Yikes. The hostess is having a little too much fun and has started to add people at the front. Let's fix that.");
            /*
             * Used to correct the hostess judgement and correctly add
             */
            customers.gamble();
            if(customers.length() == 0){
                System.out.println("Oh. The hostess got beligerent and destroyed the whole queue. Sorry Folks!");
            }
        }
    }
}
