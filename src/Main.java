
public class Main {
    public static void main(String[] args) {
       var menu = new Menu();
       var tweeter = new Tweeter();

        do{
            menu.printMenu();
            menu.askChoice();
            switch(menu.getChoice()){
                case 1:
                    //poast a tweet
                    tweeter.askTweet();
                    var t1 = new Thread(new Tweeter());
                    t1.start();
                    break;
                case 2:
                    //fetch tweets
                    break;
                case 3:
                    //fetch news
                    break;
            }

        }while(menu.getChoice() != 0);
        System.out.println("Quitting. Good bye.");
    }
}
