public class GreenState implements SignalState{


        @Override
        public void handle(
                TrafficLight trafficLight,
                TrafficSignalController controller) {

            System.out.println(
                    trafficLight.getDirection() + " → GREEN"
            );

            // GREEN means vehicles can move.

            // After GREEN duration is completed,
            // the next state should be YELLOW.

            controller.scheduleNextState(trafficLight, new YellowState());
        }

        @Override
        public String getName() {
            return "GREEN";
        }
}


