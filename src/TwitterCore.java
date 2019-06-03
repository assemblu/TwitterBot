import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Scanner;

public class TwitterCore{

    private TwitterFactory twitterFactory;
    private ConfigurationBuilder cb;

    public TwitterCore(){
        this.cb = new ConfigurationBuilder();
        this.cb.setDebugEnabled(false)
                .setOAuthConsumerKey("GaJTm3qWI31mboVcWSIcf2xaP")
                .setOAuthConsumerSecret("jzjh2Sy26LmG8XxUOu65S0aVZSCh02h5vIAv8iswTlhcotR5P2")
                .setOAuthAccessToken("1133659409878573057-hc9ZQHsx3RBlBXgYDnwUJ7wJfuqri8")
                .setOAuthAccessTokenSecret("QR0TQOkAlTcYeRJVtqgK5cWuWPpOeGUA47oT2Uekk7mrn");
        this.twitterFactory =  new TwitterFactory(this.cb.build());
    }

    public TwitterFactory getTwitterFactory() {
        return twitterFactory;
    }

    public void setTwitterFactory(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
    }

    public ConfigurationBuilder getCb() {
        return cb;
    }

    public void setCb(ConfigurationBuilder cb) {
        this.cb = cb;
    }
}
