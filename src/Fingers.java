package src;
import java.sql.Time;
import java.util.ArrayList;

public class Fingers extends Workout{
    public Fingers(String name, String desc, Time est, ArrayList<Exercise> exercise) {
        super(name, desc, est, exercise);
    }
}
