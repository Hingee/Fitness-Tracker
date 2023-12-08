package src;
import java.sql.Time;
import java.util.ArrayList;

public class PowerEdurance extends Workout{
    public PowerEdurance(String name, String desc, Time est, ArrayList<Exercise> exercise) {
        super(name, desc, est, exercise);
    }
}
