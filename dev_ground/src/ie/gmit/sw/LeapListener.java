package ie.gmit.sw;

import com.leapmotion.leap.*;
import ie.gmit.sw.leap.observers.taps.TapGestureListenerImpl;
import ie.gmit.sw.leap.observers.taps.TapGesturesListener;

public class LeapListener extends Listener{


    private ListenerDataHandler listenerDataHandler;

    private TapGesturesListener tapGesturesListener;


    public LeapListener() {
        tapGesturesListener = TapGestureListenerImpl.getInstance();
    }

    @Override
    public void onConnect(Controller controller) {

        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        controller.config().setFloat("Gesture.KeyTap.MinDownVelocity", 40.0f);
        controller.config().setFloat("Gesture.KeyTap.HistorySeconds", .2f);
        controller.config().setFloat("Gesture.KeyTap.MinDistance", 0.5f);
        controller.config().save();

    }

    @Override
    public void onFrame(Controller controller) {

        listenerDataHandler = new ListenerDataHandler();
        Frame frame = controller.frame();

        GestureList gestures = frame.gestures();

        tapGesturesListener.notifyTapGesturePos(gestures);

        HandList hands = frame.hands();

        listenerDataHandler.handleHandsData(hands);

    }


}
