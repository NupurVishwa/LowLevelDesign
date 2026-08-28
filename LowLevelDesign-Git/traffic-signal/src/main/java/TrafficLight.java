public class TrafficLight {

    // Direction of this traffic light.
    private final Direction direction;

    // Current state of this traffic light.
    //
    // Important:
    // We store the interface type, not GreenState/RedState.
    // This allows us to replace the state dynamically.
    private SignalState currentState;

    public TrafficLight(Direction direction) {

        this.direction = direction;

        // Initially every light is RED.
        this.currentState = new RedState();
    }

    public Direction getDirection() {
        return direction;
    }

    public SignalState getState() {
        return currentState;
    }

    // Change the current state.
    //
    // Example:
    // RED → GREEN
    // GREEN → YELLOW
    // YELLOW → RED
    public void setState(SignalState state) {
        this.currentState = state;
    }

    // Delegate behavior to the current state.
    public void handle(
            TrafficSignalController controller) {

        currentState.handle(
                this,
                controller
        );
    }
}