import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Scanner;

public class Tweeter extends TwitterCore implements Runnable{

    private static String tweet;

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
            Twitter twitter = super.getTwitterFactory().getInstance();
            Status status = twitter.updateStatus(getTweet());
        }catch(TwitterException e){
            System.err.println(e);
        }
    }

    @Override
    synchronized public void run() {
        postTweet();
    }
}
