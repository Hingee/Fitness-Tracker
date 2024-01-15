package src;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTable;


public class AnalyticsPage extends Page {
   private JTable table;

   public AnalyticsPage(PageManager var3) {
      super("Analytics", var3);
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