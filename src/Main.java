import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //twitterCore object will fetch the twitter network as it is constructed.
        Thread twitterThread = new Thread(new TwitterCore("Twitter Bot Thread"));
        twitterThread.start();
    }
}
