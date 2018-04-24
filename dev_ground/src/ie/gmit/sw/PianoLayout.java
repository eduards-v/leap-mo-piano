package ie.gmit.sw;

import ie.gmit.sw.leap.observers.taps.TapGestureListenerImpl;
import ie.gmit.sw.leap.observers.taps.TapGesturesListener;
import ie.gmit.sw.leap.observers.taps.TapGesturesObserver;
import ie.gmit.sw.resources.NotesContainer;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;

import java.util.Map;

public class PianoLayout implements TapGesturesObserver {

    private double sceneCenterX = 400;
    private double sceneCenterY = 250;

    private TapGesturesListener tapGesturesListener;

    @FXML
    private HBox keyboard;

    @FXML
    void initialize(){
        initEvents();
    }


    private void initEvents(){
        Map notes = NotesContainer.getInstance().getContainer();
        tapGesturesListener = TapGestureListenerImpl.getInstance();

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
    public void captureTapGesture(double tapXPos, double tapYPos) {
        double tapX = sceneCenterX + tapXPos;
        double tapY = sceneCenterY + tapYPos;
        System.out.println("Capturing Taps location: " + tapX + " " + tapY);
        findKeyTapped(tapX, tapY);
    }

    private void findKeyTapped(double tapX, double tapY){
        keyboard.getChildren().forEach(node -> {
            Button btn = (Button)node;
            Bounds boundsToScene = btn.localToScene(btn.getBoundsInLocal());

            if(boundsToScene.contains(tapX, tapY)){

                btn.fire();

            }

        });
    }
}
