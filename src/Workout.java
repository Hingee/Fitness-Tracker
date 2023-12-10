package src;

import java.util.ArrayList;

public abstract class Workout {
    public String name;
    public String desc;
    public int est;
    public String[] exercises;

    public Workout(String n, String d, int e, String[] ex) {
        name = n;
        desc = d;
        est = e;
        exercises = ex;
    }

    public Workout() {
        name = "";
        desc = "";
        est = 0;
        String[] temp = {""};
        exercises = temp;
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
    
    public ArrayList<Exercise> getExercises(ArrayList<Exercise> exerciseList) {
        for(Exercise e: exerciseList) {
            for(String ex: exercises) {
                if(!e.getName().equals(ex)) {
                    exerciseList.remove(e);
                }
            }
        }
        return exerciseList;
    }
}
