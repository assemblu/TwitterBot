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
                .setOAuthConsumerKey("key")
                .setOAuthConsumerSecret("secret")
                .setOAuthAccessToken("token")
                .setOAuthAccessTokenSecret("tokensecret");
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
