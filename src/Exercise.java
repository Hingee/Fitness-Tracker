package src;

public class Exercise {
    String name;
    int sets;
    int repsPerSet;
    int rest;
    String difficulty;

    public Exercise(String n, int s, int rps, int r, String diff) {
       name = n;
       sets = s;
       repsPerSet = rps;
       rest = r;
       difficulty=diff;
    }
}
