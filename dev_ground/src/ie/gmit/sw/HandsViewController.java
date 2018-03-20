package ie.gmit.sw;

import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
    public synchronized void addHandView(HandSide handSide){
        System.out.println("Adding Hand");
        if(root != null){
            switch (handSide){
                case HAND_R:
                            if(handr.compareAndSet(0 , 1)) {
                                root.getChildren().addAll(hands.get(handSide).getHand_view());
                                System.out.println("Right hand added");
                            }
                            break;
                case HAND_L:
                            if(handl.compareAndSet(0 , 1)){
                                root.getChildren().addAll(hands.get(handSide).getHand_view());
                                System.out.println("Left hand added");
                            }
                            break;
            }
        }
    }

    // Add hand to view
    public void addHandView(){
        if(root != null){
            hands.forEach((s, handView) -> root.getChildren().addAll(handView.getHand_view()));
        }
    }

    // Remove hand from view
    public synchronized void removeHandView(HandSide handSide){
        if(root != null){
            switch (handSide){
                case HAND_R:
                    if(handr.compareAndSet(1, 0)) {
                        root.getChildren().removeAll(hands.get(handSide).getHand_view());
                        System.out.println("Right hand removed");
                    }
                    break;
                case HAND_L:
                    if(handl.compareAndSet(1, 0)){
                        root.getChildren().removeAll(hands.get(handSide).getHand_view());
                        System.out.println("Left hand removed");
                    }
                    break;
            }

            //root.getChildren().removeAll(handView.getHand_view());
        }
    }


}
