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
    void initialize(){
        initEvents();
    }

    private void initEvents(){
        Map notes = NotesContainer.getInstance().getContainer();
        a1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("do_28");
            audio.play();
        });
        b1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("re_30");
            audio.play();
        });
        c1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("mi_32");
            audio.play();
        });
        d1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("fa_33");
            audio.play();
        });
        e1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("sol_35");
            audio.play();
        });
        f1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("la_37");
            audio.play();
        });

        g1.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("si_39");
            audio.play();
        });

        a2.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get("do_40");
            audio.play();
        });


    }
}
