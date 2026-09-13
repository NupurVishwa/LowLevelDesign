package Model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
    // Defines a TimeSlot record that stores the day, start time, and end time of a course.


    public boolean overlaps(TimeSlot other) {
        // Checks whether this time slot overlaps with another time slot.


        return this.day == other.day &&
                // First checks whether both time slots are on the same day.


                this.startTime.isBefore(other.endTime) &&
                // Checks whether this time slot starts before the other time slot ends.


                this.endTime.isAfter(other.startTime);
        // Checks whether this time slot ends after the other time slot starts.
        // If all three conditions are true, the two time slots overlap.
    }
}