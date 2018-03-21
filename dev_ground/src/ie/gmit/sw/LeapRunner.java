package ie.gmit.sw;

import com.leapmotion.leap.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.font.BidiUtils;


public class LeapRunner extends Application {

    private Controller c;
    private Listener l;

    private HandsViewController handsViewController = HandsViewController.getInstance();
    private TapGestureController tapGestureController = TapGestureController.getInstance();

    @Override
    public void start (Stage primaryStage) {

        StackPane root = new StackPane();
        handsViewController.setUiRoot(root);
        tapGestureController.setUiRoot(root);


        System.out.println("Elements count in root: " + root.getChildren().size());
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);


        root.getChildren().forEach(node -> {
            Bounds boundsInPatent = node.localToParent(node.getBoundsInLocal());

            System.out.println(boundsInPatent.getMinX() + " " + boundsInPatent.getMinY());
        });


        c = new Controller();
        l = new LeapListener();
        c.addListener (l);


        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
