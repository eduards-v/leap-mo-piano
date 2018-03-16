package ie.gmit.sw;

import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

public class HandsViewController {

    private static HandsViewController instance;
    private Map<String, HandView> hands;
    private Node root = null;

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

    public void setUiRoot(Node root){
        this.root = root;
    }

    public Map<String, HandView> getHands() {
        return hands;
    }
}
