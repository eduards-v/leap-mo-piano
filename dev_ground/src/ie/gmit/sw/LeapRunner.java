package ie.gmit.sw;

import com.leapmotion.leap.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class LeapRunner extends Application {

    private Controller c;
    private Listener l;

    private HandsViewController handsViewController = HandsViewController.getInstance();

    @Override
    public void start (Stage primaryStage) {

        StackPane root = new StackPane();
        handsViewController.setUiRoot(root);
        handsViewController.addHandView();

//        ui_hands.getFingers()
//                .forEach(ui_finger
//                -> root.getChildren()
//                        .add(ui_finger.getFinger()));

        System.out.println("Elements count in root: " + root.getChildren().size());
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);

        c = new Controller();
        l = new LeapListener();
        c.addListener (l);


        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
