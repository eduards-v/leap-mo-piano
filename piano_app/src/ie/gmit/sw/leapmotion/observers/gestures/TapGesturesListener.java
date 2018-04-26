package ie.gmit.sw.leapmotion.observers.gestures;

import com.leapmotion.leap.GestureList;

public interface TapGesturesListener {

    void register(TapGesturesObserver observer);
    void notifyTapGesturePos(GestureList gestures);
}
