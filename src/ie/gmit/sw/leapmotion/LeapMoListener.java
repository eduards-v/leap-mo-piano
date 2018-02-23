package ie.gmit.sw.leapmotion;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

public class LeapMoListener extends Listener{

    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    @Override
    public void onFrame(Controller controller) {

        // enabling key tap event, off by default
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        // get all data from controller frame
        Frame frame = controller.frame();

        // used to pass event to JavaFX application thread
//        Platform.runLater(() -> {
//
//        });

    }
}
