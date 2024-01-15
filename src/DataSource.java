package src;
import java.util.ArrayList;
import java.util.Optional;
//Singelton
public class DataSource {
    private static DataSource instance = null;
    private static ArrayList<Exercise> exercises;
    private static ArrayList<Workout> workouts;

    private DataSource () {
        exercises = new ArrayList<>();
        workouts = new ArrayList<>();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public void setExercises(ArrayList<Exercise> ex) {
        exercises = ex;
    }

    public void setWorkouts(ArrayList<Workout> work) {
        workouts = work;
    }

    public static ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public static ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public static Optional<Exercise> getExerciseByName(String name) {
        for (Exercise e : exercises) {
            if (e.getName().equals(name)) {
                return Optional.of(new Exercise(name, e.getSets(), e.getRepsPerSet(), e.getRestMinRep(), e.getRestMinSet(), e.getDifficulty()));
            }
        }
        return Optional.empty();
    }
}
