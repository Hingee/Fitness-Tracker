package src;

import java.util.ArrayList;

public abstract class Workout {
    public String name;
    public String desc;
    public int est;
    public ArrayList<Exercise> exercises;

    public Workout(String n, String d, int e,  ArrayList<Exercise> ex) {
        name = n;
        desc = d;
        est = e;
        exercises = ex;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public int getEst() {
        return est;
    }
    
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
