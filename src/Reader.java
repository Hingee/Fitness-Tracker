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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;
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
        ArrayList<Exercise> exercises;
        ArrayList<Exercise> workouts;

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (!line.isEmpty() && line.charAt(0) == '-') {
                if (line.endsWith("Exercises")) {
                    readExercises(scanner, exercises);
                } else if (line.endsWith("Workouts")) {
                    readWorkouts(scanner, workouts);
                }
            }
        }

        return new App(exercises, workouts);
    }

    private static boolean duplicateExercise(String name, ArrayList<Exercise> exercises) {
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
    private static void readExercises(Scanner sc, ArrayList<Exercise> exercises) {
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
                String err = String.format("The item name '%s' is used multiple times", name);
                System.err.println(err);
                System.exit(0);
            }
            int sets = Integer.valueOf(parts[1].trim());
            int repsPerSet = Integer.valueOf(parts[2].trim());
            Time restMin = Time.valueOf(parts[3].trim());
            String difficulty = parts[4].trim();

            Exercise exercise = new Exercise(name, sets, repsPerSet, restMin, difficulty);
            exercises.add(exercise);
            itemLine = sc.nextLine();
        } while (sc.hasNextLine() && !itemLine.isEmpty());

    }

    // line format
    // {NAME}, {DESCRIPTION}, {COMPONENT 1}, {COMPONENT 2}, ...
    private static void readWorkouts(Scanner sc, ArrayList<ItemDefinition> defs) {
        if (Reader.workoutsRead) {
            System.err.println("Craftable Items in data file are not stored together");
            System.exit(0);
        }
        Reader.workoutsRead = true;

        String itemLine = sc.nextLine();
        do {
            String[] parts = itemLine.split(",");
            
            String name = parts[0].trim();
            // Disallow duplicate item names
            if (Reader.duplicateItemName(name, defs)) {
                String err = String.format("The item '%s' is defined multiple times", name);
                System.err.println(err);
                System.exit(0);
            }
            String description = parts[1].trim();

            // 

            String[] components = new String[parts.length - 2];
            for (int i = 2; i < parts.length; i++) {
                components[i - 2] = parts[i].trim();
            }
            
            ItemDefinition itemDefinition = new ItemDefinition(name, description, Optional.empty(), components);
            
            defs.add(itemDefinition);
            itemLine = sc.nextLine();
        } while (sc.hasNextLine() && !itemLine.isEmpty());

    }

    /**
     * Line format:
     * {NAME | WEIGHT CAPACITY}, {ITEM NAME}, {QTY}, {ITEM NAME}, {QTY}, ...
     * @param data - The result of splitting the `player` or `store` line of the config by ","
     * @return
     */
    private static Inventory readStartingItems(
        Scanner sc,
        ArrayList<ItemDefinition> itemDefinitions
    ) {
        Inventory startingInventory = new Inventory();

        String line = sc.nextLine();
        while (!line.isEmpty() && sc.hasNextLine()) {
            String[] data = line.split(",");
            String name = data[0].trim();
            int qty = Integer.valueOf(data[1].trim());
    
            getItemDef(name, itemDefinitions).ifPresentOrElse(
                (def) -> {
                    for (int i = 0; i < qty; i++) {
                        startingInventory.addOne(def.create());
                    }
                },
                () -> {
                    System.err.println("Bad starting item '" + name + "' was read. Exiting early");
                    System.exit(0);
                });
            line = sc.nextLine();
        }
        // One left behind
        String[] data = line.split(",");
        if (!line.isBlank()) {
            int qty = Integer.valueOf(data[1].trim());
            getItemDef(data[0], itemDefinitions).ifPresentOrElse(
                (def) -> {
                    for (int i = 0; i < qty; i++) {
                        startingInventory.addOne(def.create());
                    }
                },
                () -> {
                    System.err.println("Bad starting item '" + data[0] + "' was read. Exiting early");
                    System.exit(0);
                });
        }

        return startingInventory;
    }
}

