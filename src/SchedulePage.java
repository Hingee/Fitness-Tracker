package src;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SchedulePage extends Page{
       private JTable table;

   public SchedulePage(PageManager var3) {
      super("Scheduler", var3);
   }

   protected void init() {
   }

   protected JPanel getCenter() {
      JPanel var1 = new JPanel(new GridLayout(0, 2));

      return var1;
   }

   protected JPanel getBottom() {
      return new JPanel();
   }
}