package ie.gmit.sw.leap.observers.feedback;

public interface FeedBackListener {

    void register(FeedbackObserver observer);
    void notifyNewFeedback(char[] keys);

}
