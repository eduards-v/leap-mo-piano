package ie.gmit.sw.leap.observers;

import com.leapmotion.leap.GestureList;

public interface TapGesturesListener {

    void register(TapGesturesObserver observer);
    void notifyTapGesturePos(GestureList gestures);
}
