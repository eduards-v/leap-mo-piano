# Piano sample with Leap Motion

Simple 8 keys piano application developed with JavaFX to demonstrate capabilities of Leap Motion.

## Leap Motion overview

A Leap Motion is a hand tracking device that creates an interface between real world and computer technologies. 
Device is feeted with optical sensors and infrared light to recognize and track hands and fingers.
Field of sensors view is about 150 degrees in standard operating position and effective range of
vision extends approximately 25 to 600 millimeters above the device (2.5cm to 60cm).

![](https://di4564baj7skl.cloudfront.net/documentation/images/Leap_View.jpg)

_The Leap Motion controller’s view of your hands_

The Leap Motion software combines its sensor data with an internal model of the human hand to help cope with challenging tracking conditions.

### Coordinate system

The Leap Motion system employs a right-handed Cartesian coordinate system. The origin is centered at the top of the Leap Motion Controller.
The x- and z-axes lie in the horizontal plane, with the x-axis running parallel to the long edge of the device. 
The y-axis is vertical, with positive values increasing upwards (in contrast to the downward orientation of most computer 
graphics coordinate systems). The z-axis has positive values increasing toward the user.

![](https://di4564baj7skl.cloudfront.net/documentation/images/Leap_Axes.png)


### Leap Motion Java API overview

To begin development with LeapMotion in Java, download SDK and follow instruction to setup development environment:
https://developer.leapmotion.com/get-started/

As the Leap Motion controller tracks hands and fingers in its field of view, it provides updates as a set – or frame – of data. 
Each Frame object representing a frame contains any tracked hands, detailing their properties at a single moment in time. 
The Frame object is essentially the root of the Leap Motion data model.

#### Controller & Listener classes

Minimal application with LeapMotion requires to instantiate two classes, `Controller` from SDK and your custom class
that extends `Listener` class from SDK. `Controller` has a method to set your custom listener as a receiver for incoming data frames.
Your listener should override at least 2 methods, `onConnected` and `onFrame` from `Listener` class it extends. Method parameter is a 
`Controller` itself from which you get `Frame`. 

```Java
public class MyApp {

    public static void main(String[] args) {
        Controller c = new Controller();
        Listener l = new LeapListener(); // Instantiate your class that extends Listener
        c.addListener(l); // Add listener to Controller
    }
}

public class LeapListener extends Listener {

    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        
        // Get data from frame and do something with it...
    }
}
```

It is recommended to configure `Controller` in `onConnect` method in your listener class. For example, to use gestures, it is required
to activate it through `Controller`, as well as, configuring settings for enchanced performance.

```Java
@Override
public void onConnect(Controller controller) {
    System.out.println("Connected");
    
    // Activate Key Tap gesture
    controller.enableGesture(Gesture.Type.TYPE_KEY_TAP); 
    
    // Customize Key Tap gesture 
    controller.config().setFloat("Gesture.KeyTap.MinDownVelocity", 40.0f);
    controller.config().setFloat("Gesture.KeyTap.HistorySeconds", .2f);
    controller.config().setFloat("Gesture.KeyTap.MinDistance", 0.5f);
    controller.config().save();
}
```

#### Frame class

This class represents core data model from where you can access other models like `Hand`, `Finger`, `Gesture`, etc.
Most of these models are contained within respective list classes. 

```Java
@Override
public void onFrame(Controller controller) {
    // get the frame
    Frame frame = controller.frame();
    
    // list of gestures
    GestureList gestures = frame.gestures();
    
    // can be simply itterated with for loop, forEach with lambda or with Itterator.
    for(Gesture gesture : gestures){
        gesture.type(); // check type of gesture to create valid instance of gesture
    }
   
    // list of hands 
    HandList hands = frame.hands();
    
    // itterate over list with hands 
    hands.forEach(hand -> {
        // Get fingers list from hand
        FingerList fingers = hand.fingers();
        
        // Check if hand is right or left
        hand.isRight();
        hand.isLeft();
    });

}
```

#### Hand class

`Hand` model provides information about the identity, position, and other characteristics of a detected hand, 
the arm to which the hand is attached, and lists of the fingers associated with the hand.

_Example of how to collect palm and finger tip possitions_
```Java
@Override
public void onFrame(Controller controller) {
    // get the frame
    Frame frame = controller.frame();
   
    // list of hands 
    HandList hands = frame.hands();
    
    // itterate over list with hands 
    hands.forEach(hand -> {
    
        // Palm possition
        float x = hand.palmPosition().getX();
        float y = hand.palmPosition().getY();
        float z = hand.palmPosition().getZ();
        
        
        // Get fingers list from hand
        FingerList fingers = hand.fingers();
        
        fingers.forEach(finger -> {
          float x = finger.tipPosition().getX();
          float y = finger.tipPosition().getY();
          float z = finger.tipPosition().getZ();
        });
       
    });

}
```

#### Gestures
The Leap Motion software recognizes a quick, downward tapping movement by a finger or tool as a Key Tap gesture.
For purposes of this application, I've used Key Taps gesture, although there is three more (Circle, Swipe and Screen Taps for touch screens).

![](https://di4564baj7skl.cloudfront.net/documentation/v2/images/Leap_Gesture_Tap.png)

You can make a key tap gesture by tapping downward as if pressing a piano key. Tap gestures are discrete. 
Only a single Gesture object is added per tap gesture.

`KeyTapGesture` is the model class that contains details of a key tap performed. It can be reffered back to a hand and exact finger that performed 
gesture. 

```Java
@Override
public void onFrame(Controller controller) {
    // get the frame
    Frame frame = controller.frame();
    
    // list of gestures
    GestureList gestures = frame.gestures();
   
    for(Gesture gesture : gestures){
       // match gesture to its type
       if(gesture.type() == KeyTapGesture.classType()){
       
          // Create object corresponding to a gesture type
          KeyTapGesture keyTap = new KeyTapGesture(gesture);
          
          // Possitions are stored as a Vectors 
          Vector tapPosition = keyTap.position();
          
          // Get tap possitions to map it to UI
          Float x = tapPosition.getX();
          Float y = tapPosition.getZ();
       }
    }
}
```

### Integration with JavaFX UI thread

Like in any multithreaded UI applications, we can not modify UI thread properties directly from another thread. 
In worst case scenario UI will freeze and compiler will throw runtime exception `ConcurrentModificationException`.
To avoid this happening in JavaFX development environment, use `Platform` API from JavaFX application package. 
Method `runLater` will ensure that all incoming update properties are injected correctly. 

_Example of how to handle incoming modifications from another thread_
```Java
// JavaFX UI property
@FXML
private Text possitions;

// Handling incoming data 
private void handleConcurrentModification(float x, float y, float z){
    // implement functional interface method on the fly
    Platform.runLater(() ->{
        this.possitions.setText("X: " + Float.toString(x) + "\n" + 
                                "Y: " + Float.toString(y) + "\n" + 
                                "Z: " + Float.toString(z));
    });
}
```

### Application Architecture




















