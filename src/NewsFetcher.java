import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewsFetcher implements Runnable{

    private static String newstitle;
    private static String userName;

    public NewsFetcher(){

    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        NewsFetcher.userName = userName;
    }

    @Override
    synchronized public void run() {
        //Implement news fetcher from RSS
        System.out.println("RSS Reader is running");
        try{
            URL feedSource = new URL("https://news.google.com/rss?hl=en-US&gl=US&ceid=US:en");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));

            for(var o : feed.getEntries()){
                SyndEntry entry = (SyndEntry) o;

                if(entry.getTitle().toUpperCase().contains(newstitle.toUpperCase())){
                    var tweeter = new Tweeter();
                    var tweetThread = new Thread(new Tweeter());
                    var sb = new StringBuilder();

                    sb.append(" - " + entry.getTitle() + " " + entry.getLink());
                    tweeter.setTweet(sb.toString());
                    tweetThread.start();

                    break;
                }
            }
        }catch(Exception e){
            System.err.println(e);
        }

    }
}
