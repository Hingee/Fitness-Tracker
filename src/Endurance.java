package src;
import java.sql.Time;
import java.util.ArrayList;

public class Endurance extends Workout {
    String name;
    String desc;
    Time est;
    ArrayList<Exercise> exercises = new ArrayList<>();

    public Endurance(String n, String d, Time e, ArrayList<Exercise> ex) {
        name = n;
        desc = d;
        est = e;
        exercises = ex;
    }
}
