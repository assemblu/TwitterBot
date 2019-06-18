
public class Main {
    public static void main(String[] args) {
       var menu = new Menu();
       var tweeter = new Tweeter();

        do{
            var dmMasterThread = new Thread(new DmMaster());
            dmMasterThread.start();

            menu.printMenu();
            menu.askChoice();
            switch(menu.getChoice()){
                case 1:
                    //poast a tweet
                    tweeter.askTweet();
                    //offloading tweeting task to another thread
                    var tweeterThread = new Thread(new Tweeter());
                    tweeterThread.start();
                    break;
            }
        }while(menu.getChoice() != 0);
        System.out.println("Quitting. Good bye.");
    }
}
