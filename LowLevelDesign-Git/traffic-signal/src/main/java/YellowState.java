public class YellowState implements SignalState {

    @Override
    public void handle(
            TrafficLight trafficLight,
            TrafficSignalController controller) {

        System.out.println(
                trafficLight.getDirection()
                        + " → YELLOW"
        );

        // YELLOW warns vehicles that the signal
        // is about to become RED.

        // After YELLOW duration,
        // move to RED.

        controller.scheduleNextState(
                trafficLight,
                new RedState()
        );
    }

    @Override
    public String getName() {
        return "YELLOW";
    }
}