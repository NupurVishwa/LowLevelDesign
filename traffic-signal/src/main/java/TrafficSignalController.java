import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrafficSignalController {

    // Stores all traffic lights.
    private final Map<Direction, TrafficLight> signals;

    // Stores duration for every direction and state.
    //
    // Example:
    //
    // NORTH:
    // GREEN  = 5 seconds
    // YELLOW = 2 seconds
    // RED    = 3 seconds
    private final Map<Direction, Map<String, Integer>>
            signalDurations;

    // Scheduler is used to automatically change signals
    // after a configured amount of time.
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public TrafficSignalController(
            Map<Direction, TrafficLight> signals,
            Map<Direction, Map<String, Integer>> signalDurations) {

        this.signals = signals;
        this.signalDurations = signalDurations;
    }

    // Start the traffic system with a particular direction.
    public void start(Direction direction) {

        System.out.println(
                "Starting traffic signal with "
                        + direction
        );

        switchToGreen(direction);
    }

    // Make a particular direction GREEN.
    public void switchToGreen(Direction direction) {

        System.out.println(
                "\nSwitching "
                        + direction
                        + " to GREEN"
        );

        // First make every other direction RED.
        //
        // This ensures that two directions don't become
        // GREEN simultaneously.
        for (TrafficLight light : signals.values()) {

            if (light.getDirection() != direction) {

                light.setState(
                        new RedState()
                );
            }
        }

        // Make selected direction GREEN.
        TrafficLight light =
                signals.get(direction);

        light.setState(
                new GreenState()
        );

        // Execute the GREEN state's behavior.
        light.handle(this);
    }

    // Schedule the next state.
    public void scheduleNextState(
            TrafficLight trafficLight,
            SignalState nextState) {

        Direction direction =
                trafficLight.getDirection();

        String currentState =
                trafficLight.getState().getName();

        // Find how long current state should remain active.
        int duration =
                getSignalDuration(
                        direction,
                        currentState
                );

        System.out.println(
                direction
                        + " stays "
                        + currentState
                        + " for "
                        + duration
                        + " seconds"
        );

        // After duration seconds, move to next state.
        scheduler.schedule(
                () -> {

                    trafficLight.setState(
                            nextState
                    );

                    // Let the new state execute its behavior.
                    trafficLight.handle(this);

                },
                duration,
                TimeUnit.SECONDS
        );
    }

    // Get configured duration for a state.
    public int getSignalDuration(
            Direction direction,
            String state) {

        return signalDurations
                .get(direction)
                .getOrDefault(state, 3);
    }

    // Round-robin logic.
    //
    // NORTH → SOUTH
    // SOUTH → EAST
    // EAST  → WEST
    // WEST  → NORTH
    public Direction getNextDirection(
            Direction currentDirection) {

        Direction[] directions =
                Direction.values();

        int currentIndex =
                currentDirection.ordinal();

        int nextIndex =
                (currentIndex + 1)
                        % directions.length;

        return directions[nextIndex];
    }

    // Manual override.
    //
    // Example:
    // Someone wants EAST to become GREEN immediately.
    public void manualOverride(
            Direction direction) {

        System.out.println(
                "\nMANUAL OVERRIDE → "
                        + direction
        );

        // Immediately make selected direction GREEN.
        switchToGreen(direction);
    }

    public void shutdown() {

        // Stop scheduler when application terminates.
        scheduler.shutdown();
    }
}