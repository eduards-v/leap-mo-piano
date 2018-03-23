package ie.gmit.sw;

import com.leapmotion.leap.*;
import ie.gmit.sw.leap.observers.TapGestureListenerImpl;
import ie.gmit.sw.leap.observers.TapGesturesListener;

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
        controller.config().setFloat("Gesture.KeyTap.MinDistance", 1.0f);
        controller.config().save();

    }

    @Override
    public void onFrame(Controller controller) {

        listenerDataHandler = new ListenerDataHandler();
        Frame frame = controller.frame();

        GestureList gestures = frame.gestures();

        tapGesturesListener.notifyTapGesturePos(gestures);

//        gestures.forEach(gesture -> {
//            if(gesture.type() == KeyTapGesture.classType()){
//                KeyTapGesture keyTap = new KeyTapGesture(gesture);
//
//                Pointable tappingPointable = keyTap.pointable();
//
//                Vector tapPosition = keyTap.position();
//
//
//                System.out.println("Tap event captured!");
//                System.out.println("Position X: " + tapPosition.getX());
//                System.out.println("Position Y: " + tapPosition.getY());
//                System.out.println("Position Z: " + tapPosition.getZ());
//            }
//        });

        HandList hands = frame.hands();


        listenerDataHandler.handleHandsData(hands);

    }


}
