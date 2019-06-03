import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Scanner;

public class TwitterCore implements Runnable{

    private String name;
    private TwitterFactory twitterFactory;
    private ConfigurationBuilder cb;
    private String tweet;

    public TwitterCore(String name){
        this.name = name;
        this.cb = new ConfigurationBuilder();
        this.cb.setDebugEnabled(true)
                .setOAuthConsumerKey("GaJTm3qWI31mboVcWSIcf2xaP")
                .setOAuthConsumerSecret("jzjh2Sy26LmG8XxUOu65S0aVZSCh02h5vIAv8iswTlhcotR5P2")
                .setOAuthAccessToken("1133659409878573057-hc9ZQHsx3RBlBXgYDnwUJ7wJfuqri8")
                .setOAuthAccessTokenSecret("QR0TQOkAlTcYeRJVtqgK5cWuWPpOeGUA47oT2Uekk7mrn");
        this.twitterFactory =  new TwitterFactory(this.cb.build());
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void askTweet(){
        Scanner reader = new Scanner(System.in);
        System.out.print("Tweet: ");
        setTweet(reader.nextLine());
    }

    public void postTweet(){
        try{
            Twitter twitter = twitterFactory.getInstance();
            Status status = twitter.updateStatus(getTweet());
        }catch(TwitterException e){
            System.err.println(e);
        }
    }

    public void run(){
        try{
            //will run during thread
            askTweet();
            postTweet();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
