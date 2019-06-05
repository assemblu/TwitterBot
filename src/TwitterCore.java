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
                .setOAuthConsumerKey("sU24XDPs9BvyqoRoIo0mc819q")
                .setOAuthConsumerSecret("LFmw19k9Cucf1Nb7mGlKAGn9Qa33ioZzUpTf88UXEk9g6Z1AJX")
                .setOAuthAccessToken("1133659409878573057-t5ub09ySW68LqigKaBZIudtNvmCrvd")
                .setOAuthAccessTokenSecret("aDe6KJfwyL1SVIlqZh7qMy9Jxv2McJx9LaSu9nigttymU");
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
