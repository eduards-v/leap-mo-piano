package ie.gmit.sw;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;
import ie.gmit.sw.leapmotion.LeapMoListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PianoApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("gui/pages/piano.fxml"));

        // get the keyboard from Parent node.
        Group keyboard = (Group) root.getChildrenUnmodifiable().get(0);
        Controller leapController = new Controller();
        // pass a list of keys to leap listener to trigger events
        Listener leapListener = new LeapMoListener(keyboard.getChildren());
        leapController.addListener(leapListener);

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("PIANO");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
