package src;
import java.sql.Time;
import java.util.ArrayList;

public class Workout {
    public String name;
    public String desc;
    public Time est;
    public ArrayList<Exercise> exercises = new ArrayList<>();

    public Workout(String n, String d, Time e, ArrayList<Exercise> ex) {
        name = n;
        desc = d;
        est = e;
        exercises = ex;
    }

    public String getName() {
        return name;
    }
}
