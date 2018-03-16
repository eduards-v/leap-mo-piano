package ie.gmit.sw;

import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

public class HandsViewController {

    private HandsViewController instance;
    private Map<String, HandView> hands;
    private Node root = null;

    private HandsViewController() {
        hands = new HashMap<>();
    }

    public HandsViewController getInstance() {
        if(instance == null){
          instance = new HandsViewController();
          hands.put("hand1", new HandView("hand1"));
          hands.put("hand2", new HandView("hand2"));
        }
        return instance;
    }

    public void setUiRoot(Node root){
        this.root = root;
    }


}
