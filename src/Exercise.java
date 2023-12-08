package src;
import java.sql.Time;

public class Exercise {
    String name;
    int sets;
    int repsPerSet;
    Time restMin;
    String difficulty;

    public Exercise(String n, int s, int rps, Time r, String diff) {
       name = n;
       sets = s;
       repsPerSet = rps;
       restMin = r;
       difficulty=diff;
    }

    public String getName() {
        return name;
    }
}
