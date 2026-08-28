// State Pattern:
//
// Traffic light can be in different states:
// GREEN, YELLOW and RED.
//
// Instead of putting all state-specific logic inside
// TrafficLight, we create separate classes for each state.

public interface SignalState {

    // Each state decides what should happen next.
    void handle(TrafficLight trafficLight,
                TrafficSignalController controller);

    // Returns the name of the state.
    String getName();
}
