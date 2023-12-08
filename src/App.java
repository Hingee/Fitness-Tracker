package src;
import java.util.ArrayList;
import javax.swing.JFrame;

public class App {
    private ArrayList<Exercise> exercises;
    private ArrayList<Workout> workouts;
    private PageManager manager;
    private JFrame frame;

    public App(ArrayList<Exercise> exercises, ArrayList<Workout> workouts) {
        this.exercises = exercises;
        this.workouts = workouts;
        this.manager = new PageManager(this.workouts, this.exercises);

        // Window creation
        manager.refresh();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(manager.getJPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
