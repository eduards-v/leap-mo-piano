package ie.gmit.sw;

import ie.gmit.sw.leap.observers.feedback.FeedbackListenerImpl;
import ie.gmit.sw.leap.observers.taps.TapGestureListenerImpl;
import ie.gmit.sw.leap.observers.taps.TapGesturesListener;
import ie.gmit.sw.leap.observers.taps.TapGesturesObserver;
import ie.gmit.sw.resources.NotesContainer;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;

import java.util.Map;

public class PianoLayout implements TapGesturesObserver {

    private double sceneCenterX = 400;
    private double sceneCenterY = 250;

    @FXML
    private HBox keyboard;

    @FXML
    void initialize(){
        initEvents();
    }


    private void initEvents(){
        Map notes = NotesContainer.getInstance().getContainer();
        TapGesturesListener tapGesturesListener = TapGestureListenerImpl.getInstance();

        keyboard.getChildren().forEach(node -> {
            Button btn = (Button)node;
            btn.setOnAction(event -> {
                AudioClip audio = (AudioClip)notes.get(node.getId());
                audio.play();
                System.out.println(node.getLayoutX());

            });
        });

        // register to be an observer for Leap Motion tap events
        tapGesturesListener.register(this);
    }


    @Override
    public void captureTapGestures(double[] tapXPos, double[] tapYPos) {

        char[] feedback = new char[tapXPos.length];
        // traverse incoming arrays and construct feedback []
        for(int i = 0; i < feedback.length; i++){

            // notify application with new feedback
            double tapX = sceneCenterX + tapXPos[i];
            double tapY = sceneCenterY + tapYPos[i];
            System.out.println("Capturing Taps location: " + tapX + " " + tapY);

            // collect feedback on keys pressed
            feedback[i] = findKeyTapped(tapX, tapY);

        }

        // notify observers with new feedback from frame
        FeedbackListenerImpl.getInstance().notifyNewFeedback(feedback);
    }

    private char findKeyTapped(double tapX, double tapY){

        char note = 'A'; // default

        for(Node node : keyboard.getChildren()) {
            Button btn = (Button)node;
            Bounds boundsToScene = btn.localToScene(btn.getBoundsInLocal());

            if(boundsToScene.contains(tapX, tapY)){

                // get button text for feedback
                note = btn.getText().charAt(0);

                // fire an event
                btn.fire();

                // return feedback

            }
        }

        return note;
    }
}
