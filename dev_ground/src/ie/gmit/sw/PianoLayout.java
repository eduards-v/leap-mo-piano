package ie.gmit.sw;

import ie.gmit.sw.leap.observers.TapGestureListenerImpl;
import ie.gmit.sw.leap.observers.TapGesturesListener;
import ie.gmit.sw.leap.observers.TapGesturesObserver;
import ie.gmit.sw.resources.NotesContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;

import java.util.Map;

public class PianoLayout implements TapGesturesObserver {

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

        tapGesturesListener.register(this);
    }


    @Override
    public void captureTapGesture(double tapXPos, double tapYPos) {
        System.out.println("Capturing Taps location: " + tapXPos + " " + tapYPos);
    }
}
