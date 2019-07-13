import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.Random;

public class NewsPoster implements Runnable{

    private long timeStart;

    public NewsPoster(){
        this.timeStart = System.currentTimeMillis();
    }

    public void postNews() {
                try {

                    String[] cities = {"quebec", "toronto", "warsaw", "amsterdam", "rotterdam", "riga", "oslo", "stockholm", "prague", "sydney", "london", "berlin", "beijing", "delhi", "tokyo", "california", "iowa", "zurich", "geneva"};
                    var random = new Random();
                    URL feedSource = new URL("https://news.google.com/news/rss/headlines/section/geo/" + cities[random.nextInt(cities.length - 1)]);
                    SyndFeedInput input = new SyndFeedInput();
                    SyndFeed feed = input.build(new XmlReader(feedSource));

                    for (var o : feed.getEntries()) {
                        SyndEntry entry = (SyndEntry) o;

                        var tweeter = new Tweeter();
                        tweeter.setTweet(entry.getTitle() + " " + entry.getLink());
                        tweeter.postTweet();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e);
                }
    }

    @Override
    public void run() {
        while(true) {
            if (System.currentTimeMillis() - timeStart > 60000) {

                var newsPoster = new NewsPoster();
                newsPoster.postNews();

                timeStart = System.currentTimeMillis();
            }

        }
    }
}
