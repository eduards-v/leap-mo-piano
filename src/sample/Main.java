package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("pages/piano.fxml"));

        Rectangle rec = new Rectangle(20, 400, 300, 300);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("PIANO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
