import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DMSender extends TwitterCore{

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

    public void run() {
        try{
            // Fetching new dms via new twitter object
            var twitter = super.getTwitterFactory().getInstance();

            twitter.sendDirectMessage(getIdentification(), getMessage());
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
