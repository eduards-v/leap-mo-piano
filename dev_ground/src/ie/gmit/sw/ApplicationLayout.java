package ie.gmit.sw;

import ie.gmit.sw.leap.observers.feedback.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ApplicationLayout implements FeedbackObserver {


    @FXML
    private TextField feedBack;


    @FXML
    void initialize(){
        // register as feedback observer
        FeedbackListenerImpl.getInstance().register(this);
    }

    @Override
    public void updateFeedback(char[] notes) {

        printFeedback(new String(notes));
    }

    private void printFeedback(String feedback){

        String current = this.feedBack.getText();
        if(current.length() > 50){
            this.feedBack.setText(feedback);
        }else{
            this.feedBack.setText(current.concat(feedback + " - "));
        }

    }
}
