package ie.gmit.sw;

import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class HandsViewController {

    private static HandsViewController instance;

    // Nodes that represent hands visualization
    private Map<String, HandView> hands;

    // Could be any FX node. In this app case it's StackPane.
    private StackPane root = null;

    private HandsViewController() {
        hands = new HashMap<>();
        hands.put("hand_r", new HandView("hand_r"));
        hands.put("hand_l", new HandView("hand_l"));
    }

    public static HandsViewController getInstance() {
        if(instance == null){
          instance = new HandsViewController();
        }
        return instance;
    }

    // Sets root FX node for hands UI
    public void setUiRoot(StackPane root){
        this.root = root;
    }

    // Hands view access
    public Map<String, HandView> getHands() {
        return hands;
    }

    // Add hand to view
    public void addHandView(HandView handView){
        if(root != null){
            //root.getChildren().add(handView.getHand_view());
        }
    }

    // Add hand to view
    public void addHandView(){
        if(root != null){
            hands.forEach((s, handView) -> root.getChildren().addAll(handView.getHand_view()));
        }
    }

    // Remove hand from view
    public void removeHandView(HandView handView){
        if(root != null){
            root.getChildren().remove(handView.getHand_view());
        }
    }


}
