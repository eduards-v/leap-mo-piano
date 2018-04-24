package ie.gmit.sw.leap.observers.feedback;

import java.util.ArrayList;
import java.util.Collection;

public class FeedbackListenerImpl implements FeedBackListener{


    private Collection<FeedbackObserver> observers = new ArrayList<>();

    private char[] keys;

    @Override
    public void register(FeedbackObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyNewFeedback() {
        observers.forEach(observer -> observer.updateFeedback(keys));
    }

    public void keysTapped(char[] keys){
        this.keys = keys;
    }
}
