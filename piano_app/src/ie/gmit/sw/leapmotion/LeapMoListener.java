package ie.gmit.sw.leapmotion;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

import java.util.List;

public class LeapMoListener extends Listener{

    private List fxKeys;

    public LeapMoListener(List fxKeys) {
        this.fxKeys = fxKeys;
    }

    @Override
    public void onConnect(Controller controller) {
        // enabling key tap event, off by default
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        System.out.println("Connected");

        System.out.println("FX Keys List size: " + fxKeys.size());
    }

    @Override
    public void onFrame(Controller controller) {

        //System.out.println("On Frame");
        // get all data from controller frame
        Frame frame = controller.frame();

        // used to pass event to JavaFX application thread
//        Platform.runLater(() -> {
//
//        });

    }
}
