package ie.gmit.sw;

import com.leapmotion.leap.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.font.BidiUtils;

import java.io.IOException;


public class LeapRunner extends Application {

    private Controller c;
    private Listener l;

    private HandsViewController handsViewController = HandsViewController.getInstance();

    @Override
    public void start (Stage primaryStage) throws IOException {

        StackPane pianoView = FXMLLoader.load(getClass().getResource("./PianoLayout.fxml"));
        //StackPane pianoView = new StackPane();

        BorderPane border = FXMLLoader.load(getClass().getResource("./ApplicationLayout.fxml"));

        border.setCenter(pianoView);

        Scene scene = new Scene(border, 800, 500);

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
