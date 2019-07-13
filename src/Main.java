
public class Main {
    public static void main(String[] args) {

        var menu = new Menu();
        var tweeter = new Tweeter();

        var dmMasterThread = new Thread(new DmMaster());
        var deleteTimelineThread = new Thread(new DeleteTimeline());
        var newsPosterThread = new Thread(new NewsPoster());

        do{
            if(!dmMasterThread.isAlive()){
                try{
                    dmMasterThread.start();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            if(!newsPosterThread.isAlive()){
                try{
                    newsPosterThread.start();
                }catch(Exception exc){
                    exc.printStackTrace();
                }
            }

            menu.printMenu();
            menu.askChoice();
            switch(menu.getChoice()){
                case 0:
                    dmMasterThread.stop();
                    deleteTimelineThread.stop();
                    newsPosterThread.stop();
                    break;
                case 1:
                    // Posting a tweet
                    // First get the tweet
                    tweeter.askTweet();

                    // Offloading tweeting task to another
                    // thread if an active thread exists
                    // Creating another thread each time because thread dies very quickly
                    // It doesn't cause any problems as the way it is right now
                    // Creating only a single thread and reviving it was worse
                    var tweeterThread = new Thread(new Tweeter());

                    try {
                        tweeterThread.start();
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println(e);
                    }
                    break;
                case 2:
                    // Offloading the delete timeline
                    // task to another thread if an active thread exists
                    try{
                        deleteTimelineThread.start();
                    }catch(Exception e){
                        System.err.println("You are trying to delete too fast. Twitter API doesn't let you do that! Unfortunate, I know.\n" +
                                "Don't spam it please, thank you.");
                        System.err.println(e);
                    }
                    break;
            }
        }while(menu.getChoice() != 0);

        System.out.println("Quitting. Good bye.");
        return;
    }
}
