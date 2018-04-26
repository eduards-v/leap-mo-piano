package ie.gmit.sw;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;
import ie.gmit.sw.gui.controllers.HandsViewController;
import ie.gmit.sw.leapmotion.LeapListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;


public class LeapRunner extends Application {

    private Controller c;
    private Listener l;

    private HandsViewController handsViewController = HandsViewController.getInstance();

    @Override
    public void start (Stage primaryStage) throws IOException {


        StackPane pianoView = FXMLLoader.load(getClass().getResource("./gui/pages/PianoLayout.fxml"));
        //StackPane pianoView = new StackPane();

        BorderPane root = FXMLLoader.load(getClass().getResource("./gui/pages/ApplicationLayout.fxml"));

        BackgroundImage myBI= new BackgroundImage(new Image(new FileInputStream("./images/bkgr.jpg")),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(myBI));
        root.setCenter(pianoView);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        handsViewController.setUiRoot(pianoView);

        c = new Controller();
        l = new LeapListener();
        c.addListener (l);


        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
