package src;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel {
      TrackerMenu tracker;
      public Canvas() {
        setPreferredSize(new Dimension(480, 800));
        tracker = new TrackerMenu();
      }

      @Override
      public void paint(Graphics g) {
        tracker.paint(g, getMousePosition());
      }
    }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      JScrollPane scrollPane = new JScrollPane(canvas);
      this.setContentPane(scrollPane);
      this.pack();
      this.setVisible(true);
    }

    public void run() {
      while(true) {
        repaint();
      }
    }
}
