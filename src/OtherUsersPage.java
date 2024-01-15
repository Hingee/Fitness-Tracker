package src;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTable;


public class OtherUsersPage extends Page {
   private JTable table;

   public OtherUsersPage(PageManager var3) {
      super("OtherUsers", var3);
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
