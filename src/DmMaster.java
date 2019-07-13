import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
public class DmMaster extends TwitterCore implements Runnable{

    private long timeStart;
    private List<DirectMessage> messages;
    private static ArrayList<String> checkedID;


    public DmMaster(){
        this.timeStart = System.currentTimeMillis();
        this.checkedID = new ArrayList<String>();
    }

    @Override
    public void run() {
        while(true){
            if(System.currentTimeMillis() - timeStart > 60000){
                try {

                    var twitter = super.getTwitterFactory().getInstance();

                    // Loop messages
                    for (var index : twitter.getDirectMessages(50)) {
                        // If has hastag it means it was sent from a user
                        if(index.getText().contains("#")){

                            // Get the content of the hashtag
                            var indexOfHash = index.getText().indexOf("#") + 1;
                            var message = index.getText().substring(indexOfHash);
                            if(message.contains(" ")) message = message.substring(0, message.indexOf(" "));

                            // Set the rssLink
                            var sb = new StringBuilder();
                            sb.append("https://news.google.com/news/rss/headlines/section/geo/");
                            sb.append(message.toLowerCase());

                            var newsFetcher = new NewsFetcher();
                            // Set rss link in news fetcher
                            newsFetcher.setRssLink(sb.toString());
                            newsFetcher.setID(index.getSenderId());

                            // Not a thread anymore
                            newsFetcher.fetch();

                            // Destroy message
                            twitter.destroyDirectMessage(index.getId());
                        }else{
                            // Destroy sent messages
                            twitter.destroyDirectMessage(index.getId());
                        }
                    }
                }catch(Exception e){
                    System.err.println(e);
                }

                timeStart = System.currentTimeMillis();
            }
        }
    }
}
