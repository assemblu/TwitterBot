import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class NewsFetcher {

    private String newsTitle;
    private String newsLink;
    private String rssLink;
    private long ID;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(String rssLink) {
        this.rssLink = rssLink;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void fetch() {
        //Implement news fetcher from RSS
        try {
            URL feedSource = new URL(this.rssLink);
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

                //isn't a thread anymore
                dmSender.run();

                break;
            }
        } catch(Exception e){
            System.err.println(e);
        }
    }

}
