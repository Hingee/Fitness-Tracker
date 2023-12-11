package src;
import javax.swing.JFrame;

public class App {
    private PageManager manager;
    private JFrame frame;

    public App() {
        this.manager = new PageManager();

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
