import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
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
    synchronized public void run() {
        while(true){
            //System.out.print(System.currentTimeMillis() - timeStart + "\r");
            if(System.currentTimeMillis() - timeStart > 60000){
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

                    //loop messages
                    for (var index : twitter.getDirectMessages(50)) {
                        //if has hastag it means it was sent from a user
                        if(index.getText().contains("#")){

                            //get the content of the hashtag
                            var indexOfHash = index.getText().indexOf("#") + 1;
                            var message = index.getText().substring(indexOfHash);
                            if(message.contains(" ")) message = message.substring(0, message.indexOf(" "));

                            //set the rssLink
                            var sb = new StringBuilder();
                            sb.append(" https://news.google.com/news/rss/headlines/section/geo/");
                            sb.append(message.toLowerCase());

                            //set rss link in news fetcher
                            var newsFetcher = new NewsFetcher();
                            newsFetcher.setRssLink(sb.toString());
                            newsFetcher.setID(index.getSenderId());

                            //start thread for news fetcher
                            var t1 = new Thread(new NewsFetcher());
                            t1.start(); //wait for it to end

                            while(t1.isAlive()){
                                //System.out.print("Wait.\r");
                            }

                            //destroy message
                            twitter.destroyDirectMessage(index.getId());
                        }else{
                            //destroy sent messages
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
