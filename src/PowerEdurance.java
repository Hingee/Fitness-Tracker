package src;
import java.sql.Time;
import java.util.ArrayList;

public class PowerEdurance extends Workout{
    String name;
    String desc;
    Time est;
    ArrayList<Exercise> exercises;

    public PowerEdurance(String n, String d, Time e, ArrayList<Exercise> ex) {
        name = n;
        desc = d;
        est = e;
        exercises = ex;
    }
}
