import java.util.EnumMap;
import java.util.Map;

public class TrafficSignalDemo {

    public static void main(String[] args) {

        // -----------------------------------------
        // 1. Create traffic lights
        // -----------------------------------------

        Map<Direction, TrafficLight> signals =
                new EnumMap<>(Direction.class);

        // Create one traffic light for every direction.
        for (Direction direction :
                Direction.values()) {

            signals.put(
                    direction,
                    new TrafficLight(direction)
            );
        }

        // -----------------------------------------
        // 2. Configure signal durations
        // -----------------------------------------

        Map<Direction, Map<String, Integer>>
                signalDurations =
                new EnumMap<>(Direction.class);

        signalDurations.put(
                Direction.NORTH,
                Map.of(
                        "GREEN", 5,
                        "YELLOW", 2,
                        "RED", 3
                )
        );

        signalDurations.put(
                Direction.SOUTH,
                Map.of(
                        "GREEN", 4,
                        "YELLOW", 2,
                        "RED", 3
                )
        );

        signalDurations.put(
                Direction.EAST,
                Map.of(
                        "GREEN", 6,
                        "YELLOW", 2,
                        "RED", 3
                )
        );

        signalDurations.put(
                Direction.WEST,
                Map.of(
                        "GREEN", 4,
                        "YELLOW", 2,
                        "RED", 3
                )
        );

        // -----------------------------------------
        // 3. Create intersection
        // -----------------------------------------

        Intersection intersection =
                new Intersection(
                        "INTERSECTION-1",
                        signals,
                        signalDurations
                );

        // -----------------------------------------
        // 4. Start automatic cycling
        // -----------------------------------------

        intersection.start(
                Direction.NORTH
        );

        // -----------------------------------------
        // 5. Manual override
        // -----------------------------------------

        // In a real system this could be triggered
        // by an emergency vehicle or traffic operator.
        //
        // Example:
        // intersection.manualOverride(Direction.EAST);

        // Keep application running for demonstration.
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        intersection.shutdown();
    }
}