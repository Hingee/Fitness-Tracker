package src;

/*
 *
 * Reads in the input configuration file, setting up and returning an App instance.
 * This file does not need to be modified, and is here if you wish to look around.
 * 
 * File format:
 * 
 * - base items
 * {NAME}, {DESCRIPTION}, {WEIGHT}
 * 
 * - craftable items
 * {NAME}, {DESCRIPTION}, {COMPONENT 1}, {COMPONENT 2}, ...
 * 
 * - store
 * {STORAGE NAME}
 * {ITEM NAME}, {QTY}
 * {ITEM NAME}, {QTY}
 * ...
 * 
 * - player
 * {WEIGHT CAPACITY}
 * {ITEM NAME}, {QTY}
 * {ITEM NAME}, {QTY}
 * ...
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private static boolean exercisesRead;
    private static boolean workoutsRead;

    static {
        exercisesRead = false;
        workoutsRead = false;
    }

    public static App read(String filePath) {
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
                } else if (line.endsWith("Workouts")) {
                    workouts = readWorkouts(scanner, workouts);
                }
            }
        }

        return new App(exercises, workouts);
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
    // {NAME}, {DESCRIPTION}, {WEIGHT}, ...
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
    // {NAME}, {DESCRIPTION}, {COMPONENT 1}, {COMPONENT 2}, ...
    private static ArrayList<Workout> readWorkouts(Scanner sc, ArrayList<Workout> workouts) {
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
            // Disallow duplicate item names
            if (Reader.duplicateWorkout(name, workouts)) {
                String err = String.format("The workout '%s' is defined multiple times", name);
                System.err.println(err);
                System.exit(0);
            }
            String description = parts[2].trim();
            int est = Integer.valueOf(parts[3].trim());

            String[] exercises = new String[parts.length - 4];
            for (int i = 4; i < parts.length; i++) {
                exercises[i - 4] = parts[i].trim();
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

