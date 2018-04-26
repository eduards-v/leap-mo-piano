package ie.gmit.sw;

import ie.gmit.sw.leap.observers.feedback.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class ApplicationLayout implements FeedbackObserver {


    @FXML
    private Text feedBack;

    @FXML
    private GridPane feedCont;


    @FXML
    void initialize(){
        // register as feedback observer
        FeedbackListenerImpl.getInstance().register(this);
        feedCont.setAlignment(Pos.CENTER);
    }

    @Override
    public void updateFeedback(char[] notes) {

        printFeedback(new String(notes));
    }

    private void printFeedback(String feedback){

        String current = this.feedBack.getText();
        if(current.length() > 50){
            Platform.runLater(() -> this.feedBack.setText(feedback));
        }else{
            Platform.runLater(() -> this.feedBack.setText(current.concat(feedback + " - ")));

        }

    }
}
