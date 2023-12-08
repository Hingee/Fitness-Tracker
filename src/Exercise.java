package src;

public class Exercise {
    String name;
    int sets;
    int repsPerSet;
    int restMinRep;
    int restMinSet;
    String difficulty;

    public Exercise(String n, int s, int rps, int rRep, int rSet, String diff) {
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

    public int getRestMinRep() {
        return restMinRep;
    }

    public int getRestMinSet() {
        return restMinSet;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
