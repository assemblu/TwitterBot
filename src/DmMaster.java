import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
public class DmMaster extends TwitterCore implements Runnable{
    //direct message manager
    //should run continously checking for messages

    private long timeStart;
    List<DirectMessage> messages;
    private static ArrayList<String> checkedID;


    public DmMaster(){
        super();
        this.timeStart = System.currentTimeMillis();
        this.checkedID = new ArrayList<String>();
    }

    @Override
    public void run() {
        while(true){
            if(System.currentTimeMillis() - timeStart > 35000){
                //check if there is a message
                //listener
                //todo
                try {
                    var messages = getTwitterFactory().getInstance().getDirectMessages(1);
                    System.out.println(messages);
                    for (var index : messages) {

                        var newsFetcher = new NewsFetcher();
                        var newsFetcherThread = new Thread(new NewsFetcher());
                        newsFetcher.setNewstitle(index.getText());
                        newsFetcherThread.start();
                        getTwitterFactory().getInstance().destroyDirectMessage(index.getId());
                    }
                }catch(Exception e){
                    System.err.println(e);
                }

               //System.out.println("Checked DMs.");
                timeStart = System.currentTimeMillis();
            }
        }
    }
}
