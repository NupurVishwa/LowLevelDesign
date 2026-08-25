public class RedState implements SignalState {

    @Override
    public void handle(
            TrafficLight trafficLight,
            TrafficSignalController controller) {

        System.out.println(
                trafficLight.getDirection()
                        + " → RED"
        );

        // Current direction is now RED.
        //
        // Instead of making this direction GREEN again,
        // controller chooses the next direction.

        Direction nextDirection =
                controller.getNextDirection(
                        trafficLight.getDirection()
                );

        // Tell the controller to make the next
        // direction GREEN.
        controller.switchToGreen(nextDirection);
    }

    @Override
    public String getName() {
        return "RED";
    }
}
