package State;
import Enum.Direction;
import Elevator.Elevator;
import Model.Request;


public interface ElevatorState {
    void move(Elevator elevator);
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
}