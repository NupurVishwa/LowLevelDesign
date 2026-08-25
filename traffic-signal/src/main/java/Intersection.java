import java.util.EnumMap;
import java.util.Map;

public class Intersection {

    private final String id;

    // All traffic lights at this intersection.
    private final Map<Direction, TrafficLight> signals;

    // Duration configuration.
    private final Map<Direction, Map<String, Integer>>
            signalDurations;

    private final TrafficSignalController controller;

    public Intersection(
            String id,
            Map<Direction, TrafficLight> signals,
            Map<Direction, Map<String, Integer>>
                    signalDurations) {

        this.id = id;
        this.signals = signals;
        this.signalDurations = signalDurations;

        // Intersection creates the controller.
        this.controller =
                new TrafficSignalController(
                        signals,
                        signalDurations
                );
    }

    // Start automatic signal cycling.
    public void start(Direction direction) {

        controller.start(direction);
    }

    // Allow manual override.
    public void manualOverride(
            Direction direction) {

        controller.manualOverride(direction);
    }

    // Get traffic light for a direction.
    public TrafficLight getSignal(
            Direction direction) {

        return signals.get(direction);
    }

    public void shutdown() {
        controller.shutdown();
    }
}