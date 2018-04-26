package ie.gmit.sw;

import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/* Singleton Class that controls presence of a hands on a scene.
* Contains a map with pair of hands view models.
* Methods:
*  public void setUiRoot(StackPane root);
*  - It is required to initially set a root to work with.
*  Stackpane is used to allow hands to be rendered over other components in scene, like piano
*  keys, otherwise hands and keys push each other.
*
*
*  public void addHandView(HandSide handSide);
*  - Adds a single hand view to a scene based on hand side enum
*  public void removeHandView(HandSide handSide);
*  - Removes a single hand view to a scene based on hand side enum
*
*   */
public class HandsViewController {

    private static HandsViewController instance;

    // Nodes that represent hands visualization
    private Map<HandSide, HandView> hands;
    // locks for hands to track presence in UI
    private AtomicInteger handl, handr;

    // Could be any FX node. In this app case it's StackPane.
    private StackPane root = null;

    private HandsViewController() {
        hands = new HashMap<>();
        hands.put(HandSide.HAND_R, new HandView("hand_r"));
        hands.put(HandSide.HAND_L, new HandView("hand_l"));

        // To track presence of a hands within view of leap motion camera
        // AtomicInteger class from java concurrent package wraps
        // values in thread safe manner. Leap motion spawns a new thread
        // for each frame, so we can save re-rendering time by keeping track
        // of hands presence
        handl = new AtomicInteger(0);
        handr = new AtomicInteger(0);

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
    public Map<HandSide, HandView> getHands() {
        return hands;
    }

    // Add hand to view
    public void addHandView(HandSide handSide){
        //System.out.println("Adding Hand");
        if(root != null){
            switch (handSide){
                case HAND_R:
                            if(handr.compareAndSet(0 , 1)) {
                                root.getChildren().addAll(hands.get(handSide).getHand_view());
                                //System.out.println("Right hand added");
                            }
                            break;
                case HAND_L:
                            if(handl.compareAndSet(0 , 1)){
                                root.getChildren().addAll(hands.get(handSide).getHand_view());
                                //System.out.println("Left hand added");
                            }
                            break;
            }
        }
    }


    // Remove hand from view
    public void removeHandView(HandSide handSide){
        if(root != null){
            switch (handSide){
                case HAND_R:
                    if(handr.compareAndSet(1, 0)) {
                        root.getChildren().removeAll(hands.get(handSide).getHand_view());
                        //System.out.println("Right hand removed");
                    }
                    break;
                case HAND_L:
                    if(handl.compareAndSet(1, 0)){
                        root.getChildren().removeAll(hands.get(handSide).getHand_view());
                        //System.out.println("Left hand removed");
                    }
                    break;
            }

            //root.getChildren().removeAll(handView.getHand_view());
        }
    }


}
