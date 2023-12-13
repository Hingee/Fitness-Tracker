package src;

/*
 *
 * Reads in the input configuration file, setting up and returning an App instance.
 * This file does not need to be modified, and is here if you wish to look around.
 * 
 * File format:
 * 
 * - Exercises
 * {name}, {sets}, {repsPerSet}, {restMinRep}, {restMinSet}, {difficulty}
 * 
 * - Workouts
 * {type}, {name}, {description}, {estimated time}, {Exercise 1}, {Exercise 2}, ...
 * END
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private static boolean exercisesRead;
    private static boolean workoutsRead;
    static DataSource data = DataSource.getInstance();

    static {
        exercisesRead = false;
        workoutsRead = false;
    }

    public static App read(String filePath) throws Exception {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ArrayList<Exercise> exercises = new ArrayList<>();
        ArrayList<Workout> workouts = new ArrayList<>();

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (!line.isEmpty() && line.charAt(0) == '-') {
                if (line.endsWith("Exercises")) {
                    exercises = readExercises(scanner, exercises);
                    data.setExercises(exercises);
                } else if (line.endsWith("Workouts")) {
                    workouts = readWorkouts(scanner, workouts);
                    data.setWorkouts(workouts);
                }
            }
        }

        return new App();
    }

    private static boolean duplicateExercise(String name, ArrayList<Exercise> exercises) {
        if(exercises == null) {
            return false;
        }
        for (Exercise exercise : exercises) {
            if (exercise.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean duplicateWorkout(String name, ArrayList<Workout> workouts) {
        for (Workout workout : workouts) {
            if (workout.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // line format
    // {name}, {sets}, {repsPerSet}, {restMinRep}, {restMinSet}, {difficulty}
    private static ArrayList<Exercise> readExercises(Scanner sc, ArrayList<Exercise> exercises) {
        if (Reader.exercisesRead) {
            System.err.println("Exercises in data file are not stored together");
            System.exit(0);
        }
        Reader.exercisesRead = true;
        String itemLine = sc.nextLine();
        do {
            String[] parts = itemLine.split(",");
            String name = parts[0].trim();
            // Disallow duplicate item names
            if (Reader.duplicateExercise(name, exercises)) {
                String err = String.format("The exercise '%s' is used multiple times", name);
                System.err.println(err);
                System.exit(0);
            }
            int sets = Integer.valueOf(parts[1].trim());
            int repsPerSet = Integer.valueOf(parts[2].trim());
            float restMinRep = Float.valueOf(parts[3].trim());
            float restMinSet = Float.valueOf(parts[4].trim());
            String difficulty = parts[5].trim();

            Exercise exercise = new Exercise(name, sets, repsPerSet, restMinRep, restMinSet, difficulty);
            exercises.add(exercise);
            itemLine = sc.nextLine();
        } while (sc.hasNextLine() && !itemLine.isEmpty());

        return exercises;
    }

    // line format
    // {type}, {name}, {description}, {estimated time}, {Exercise 1}, {Exercise 2}, ...
    private static ArrayList<Workout> readWorkouts(Scanner sc, ArrayList<Workout> workouts) throws Exception {
        if (Reader.workoutsRead) {
            System.err.println("Workouts in data file are not stored together");
            System.exit(0);
        }
        Reader.workoutsRead = true;

        String itemLine = sc.nextLine();
        do {
            String[] parts = itemLine.split(",");
            String type = parts[0].trim();
            if(type.equals("END")) {
                break;
            }
            String name = parts[1].trim();
            // Disallow duplicate names
            if (Reader.duplicateWorkout(name, workouts)) {
                String err = String.format("The workout '%s' is defined multiple times", name);
                System.err.println(err);
                System.exit(0);
            }
            String description = parts[2].trim();
            int est = Integer.valueOf(parts[3].trim());

            ArrayList<Exercise> exercises = new ArrayList<>();
            for (int i = 4; i < parts.length; i++) {
                if(DataSource.getExerciseByName(parts[i].trim()).isEmpty()) {
                    throw new Exception("Exercise does not exist");
                }
                exercises.add(DataSource.getExerciseByName(parts[i].trim()).get());
            }

            Workout workout;
            if(type.equals("PE")) {
                workout = new PowerEndurance(name, description, est, exercises);
            } else if(type.equals("E")) {
                workout = new Endurance(name, description, est, exercises);
            } else if (type.equals("SNP")) {
                workout = new StrengthNPower(name, description, est, exercises);
            }else{
                workout = new Fingers(name, description, est, exercises);
            }
                       
            workouts.add(workout);
            itemLine = sc.nextLine();
        } while (!itemLine.isEmpty() && sc.hasNextLine());

        return workouts;
    }
}

