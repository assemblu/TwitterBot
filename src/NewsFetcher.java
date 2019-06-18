import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class NewsFetcher implements Runnable{

    private static String newsTitle;
    private static String newsLink;
    private static String rssLink;
    private static long ID;

    public NewsFetcher(){

    }

    public static long getID() {
        return ID;
    }

    public static void setID(long ID) {
        NewsFetcher.ID = ID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public static String getNewsLink() {
        return newsLink;
    }

    public static void setNewsLink(String newsLink) {
        NewsFetcher.newsLink = newsLink;
    }

    public static String getRssLink() {
        return rssLink;
    }

    public static void setRssLink(String rssLink) {
        NewsFetcher.rssLink = rssLink;
    }

    @Override
    synchronized public void run() {
        //Implement news fetcher from RSS
        try {
            URL feedSource = new URL(rssLink);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));

            for (var o : feed.getEntries()) {
                SyndEntry entry = (SyndEntry) o;

                var tweeter = new Tweeter();
                var tweetThread = new Thread(new Tweeter());
                var sb = new StringBuilder();

                setNewsTitle(entry.getTitle());
                setNewsLink(entry.getLink());

                //set dm
                var dmSender = new DMSender();
                dmSender.setIdentification(getID());
                dmSender.setMessage(entry.getTitle(), entry.getLink());

                //create a thread for the dm and run
                var t2 = new Thread(new DMSender());
                t2.start();

                while(t2.isAlive()){

                }

                break;
            }
        } catch(Exception e){
            System.err.println(e);
        }

    }
}
