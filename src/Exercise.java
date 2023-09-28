package src;

public class Exercise {
    String name;
    int sets;
    int repsPerSet;
    int repRest;
    int setRest;
    String difficulty;

    public Exercise(String n, int s, int rps, int rr, int sr, String diff) {
       name = n;
       sets = s;
       repsPerSet = rps;
       repRest = rr;
       setRest = sr;
       difficulty=diff;
    }
}
