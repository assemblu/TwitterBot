import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DMSender implements Runnable{

    private static long identification;
    private static String message;

    public static long getIdentification() {
        return identification;
    }

    public static void setIdentification(long identification) {
        DMSender.identification = identification;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String title, String link) {
        DMSender.message = title + " " + link;
    }

    @Override
    public void run() {
        try{
            //fetching new dms via new twitter object
            var cb = new ConfigurationBuilder();
            cb.setDebugEnabled(false)
                    .setOAuthConsumerKey("sU24XDPs9BvyqoRoIo0mc819q")
                    .setOAuthConsumerSecret("LFmw19k9Cucf1Nb7mGlKAGn9Qa33ioZzUpTf88UXEk9g6Z1AJX")
                    .setOAuthAccessToken("1133659409878573057-t5ub09ySW68LqigKaBZIudtNvmCrvd")
                    .setOAuthAccessTokenSecret("aDe6KJfwyL1SVIlqZh7qMy9Jxv2McJx9LaSu9nigttymU");
            var twitterFactory =  new TwitterFactory(cb.build());
            var twitter = twitterFactory.getInstance();

            twitter.sendDirectMessage(getIdentification(), getMessage());
            //System.out.println("Sent: " + getMessage() + " to " + getIdentification());
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
