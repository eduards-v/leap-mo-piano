package ie.gmit.sw.leapmotion.observers.feedback;

public interface FeedBackListener {

    void register(FeedbackObserver observer);
    void notifyNewFeedback(char[] keys);

}
