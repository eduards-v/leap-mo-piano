package ie.gmit.sw.leap.observers.feedback;

import java.util.ArrayList;
import java.util.Collection;

public class FeedbackListenerImpl implements FeedBackListener{

    private static FeedBackListener instance = new FeedbackListenerImpl();

    public static FeedBackListener getInstance() {
        return instance;
    }

    private Collection<FeedbackObserver> observers = new ArrayList<>();

    @Override
    public void register(FeedbackObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyNewFeedback(char[] keys) {
        if (keys.length != 0)
            observers.forEach(observer -> observer.updateFeedback(keys));
    }


}
