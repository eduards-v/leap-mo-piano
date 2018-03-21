package ie.gmit.sw;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class TapGestureController {

    private static TapGestureController instance;
    private List<Button> keys;
    private StackPane root = null;

    private TapGestureController() {
        keys = new ArrayList<>();

//        Rectangle r = new Rectangle(300,60,60,200);
//        r.setFill(Color.WHITE);
        Button btn = new Button("");
        btn.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-fill: white;"
                    +"-fx-min-height: 200px; -fx-min-width: 60px");
        btn.setOnAction((me -> System.out.println("CLICKED CLICKED CLICKED")));

        keys.add(btn);


    }

    // Sets root FX node for hands UI
    public void setUiRoot(StackPane root){
        this.root = root;
        setKeyLayout();
    }

    private void setKeyLayout(){
        keys.forEach(key -> {
            root.getChildren().add(key);


//            double x = root.getLayoutX() + key.getLayoutX();
//            double y = root.getLayoutY() + key.getLayoutY();
//            System.out.println("Button X: " + x + " Y:" + y);
        });
    }

    public static TapGestureController getInstance() {
        if(instance == null){
            instance = new TapGestureController();
        }
        return instance;
    }


}
