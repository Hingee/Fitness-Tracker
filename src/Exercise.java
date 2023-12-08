package src;

public class Exercise {
    String name;
    int sets;
    int repsPerSet;
    float restMinRep;
    float restMinSet;
    String difficulty;

    public Exercise(String n, int s, int rps, float rRep, float rSet, String diff) {
       name = n;
       sets = s;
       repsPerSet = rps;
       restMinRep = rRep;
       restMinSet = rSet;
       difficulty=diff;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getRepsPerSet() {
        return repsPerSet;
    }

    public float getRestMinRep() {
        return restMinRep;
    }

    public float getRestMinSet() {
        return restMinSet;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
