package src;

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public int getEst() {
        return est;
    }
}
