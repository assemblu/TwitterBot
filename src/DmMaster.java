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
                try {
                    //fetching new dms via new twitter object
                    var cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(false)
                            .setOAuthConsumerKey("sU24XDPs9BvyqoRoIo0mc819q")
                            .setOAuthConsumerSecret("LFmw19k9Cucf1Nb7mGlKAGn9Qa33ioZzUpTf88UXEk9g6Z1AJX")
                            .setOAuthAccessToken("1133659409878573057-t5ub09ySW68LqigKaBZIudtNvmCrvd")
                            .setOAuthAccessTokenSecret("aDe6KJfwyL1SVIlqZh7qMy9Jxv2McJx9LaSu9nigttymU");
                    var twitterFactory =  new TwitterFactory(cb.build());
                    var twitter = twitterFactory.getInstance();

                    for (var index : twitter.getDirectMessages(50)) {
                        if(index.getText().contains("#")){
                            var indexOfHash = index.getText().indexOf("#") + 1;
                            var message = index.getText().substring(indexOfHash);
                            if(message.contains(" ")) message = message.substring(0, message.indexOf(" "));

                            //send the message
                            twitter.sendDirectMessage(index.getSenderId(), message);
                            System.out.println("Sent: " + message + " to @" + index.getSenderId());

                            //todo
                            //destroy message
                            twitter.destroyDirectMessage(index.getId());
                        }else{
                            twitter.destroyDirectMessage(index.getId());
                        }
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
