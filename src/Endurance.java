package src;
import java.sql.Time;
import java.util.ArrayList;

public class Endurance extends Workout {
    public Endurance(String name, String desc, Time est, ArrayList<Exercise> exercise) {
        super(name, desc, est, exercise);
    }
}
