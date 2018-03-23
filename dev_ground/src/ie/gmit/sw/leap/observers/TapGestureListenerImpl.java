package ie.gmit.sw.leap.observers;

import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class TapGestureListenerImpl implements TapGesturesListener {

    private static TapGestureListenerImpl instance = new TapGestureListenerImpl();

    private Collection<TapGesturesObserver> observers = new ArrayList<>();

    private TapGestureListenerImpl() {
    }

    public static TapGestureListenerImpl getInstance() {
        return instance;
    }

    @Override
    public void register(TapGesturesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyTapGesturePos(GestureList gestures) {
        gestures.forEach(gesture -> {
            if(gesture.type() == KeyTapGesture.classType()){
                KeyTapGesture keyTap = new KeyTapGesture(gesture);

                Vector tapPosition = keyTap.position();

                System.out.println("Tap event captured!");
//                System.out.println("Position X: " + tapPosition.getX());
//                System.out.println("Position Y: " + tapPosition.getY());
//                System.out.println("Position Z: " + tapPosition.getZ());
                Float x = tapPosition.getX();
                Float y = tapPosition.getZ();

                notifyObservers(new Double(x.toString()), new Double(y.toString()));
            }
        });
    }

    private void notifyObservers(double x, double y) {
        observers.forEach(observer -> {
            observer.captureTapGesture(x, y);
        });
    }
}
