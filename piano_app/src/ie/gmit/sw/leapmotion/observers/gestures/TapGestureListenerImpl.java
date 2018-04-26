package ie.gmit.sw.leapmotion.observers.gestures;

import com.leapmotion.leap.Gesture;
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

        double[] xTaps = new double[gestures.count()];
        double[] yTaps = new double[gestures.count()];
        int ctr = 0;

        for(Gesture gesture : gestures){

            if(gesture.type() == KeyTapGesture.classType()){
                KeyTapGesture keyTap = new KeyTapGesture(gesture);

                Vector tapPosition = keyTap.position();

                System.out.println("Tap event captured!");
//                System.out.println("Position X: " + tapPosition.getX());
//                System.out.println("Position Y: " + tapPosition.getY());
//                System.out.println("Position Z: " + tapPosition.getZ());
                Float x = tapPosition.getX();
                Float y = tapPosition.getZ();

                xTaps[ctr] = new Double(x.toString());
                yTaps[ctr] = new Double(y.toString());

                ctr++;
            }
        }

        notifyObservers(xTaps, yTaps);
    }

    private void notifyObservers(double[] x, double[] y) {
        observers.forEach(observer -> observer.captureTapGestures(x, y));
    }
}
