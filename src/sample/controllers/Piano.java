package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import sample.NotesContainer;

import java.util.Map;


public class Piano {

    @FXML
    private Rectangle a1;
    @FXML
    private Rectangle b1;
    @FXML
    private Rectangle c1;
    @FXML
    private Rectangle d1;
    @FXML
    private Rectangle e1;
    @FXML
    private Rectangle f1;
    @FXML
    private Rectangle g1;
    @FXML
    private Rectangle a2;

    public Piano() {
    }

    @FXML
    void initializa(){
        initEvents();
    }

    private void initEvents(){
        Map notes = NotesContainer.getInstance().getContainer();
        a1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("a1");
            audio.play();
        });
        b1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("b1");
            audio.play();
        });
        c1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("c1");
            audio.play();
        });
        d1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("d1");
            audio.play();
        });
        e1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("e1");
            audio.play();
        });
        f1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("f1");
            audio.play();
        });

        g1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("g1");
            audio.play();
        });




    }
}
