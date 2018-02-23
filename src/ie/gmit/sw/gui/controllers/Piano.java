package ie.gmit.sw.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.media.AudioClip;
import ie.gmit.sw.resources.NotesContainer;

import java.util.Map;


public class Piano {

    @FXML
    private Group keyboard;

    public Piano() {
    }

    @FXML
    void initialize(){
        initEvents();

    }

    private void initEvents(){
        Map notes = NotesContainer.getInstance().getContainer();

        keyboard.getChildren().forEach(node -> node.setOnMouseClicked(event -> {
            AudioClip audio = (AudioClip)notes.get(node.getId());
            audio.play();
        }));

    }
}
