public class DeleteTimeline extends TwitterCore implements Runnable{

    public void deleteTimeline(){
        var twitter = super.getTwitterFactory().getInstance();
        try{
            for(var o : twitter.getUserTimeline("Botmir2")){
                twitter.destroyStatus(o.getId());
            }
        }catch(Exception e){
            System.err.println(e);
        }


    }

    @Override
    public void run() {
        deleteTimeline();
    }
}
