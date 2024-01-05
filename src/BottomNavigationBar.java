package src;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomNavigationBar extends JPanel {
   public BottomNavigationBar(PageManager var1, boolean var2) {
      Box var3 = Box.createHorizontalBox();
      Box var4 = Box.createHorizontalBox();
      JButton var5;
      if (var2) {
         var5 = new JButton("< Back");
         var5.addActionListener((var1x) -> {
            var1.back();
         });
         var4.add(var5);
      }
      var3.add(var4);
      this.add(var3);
   }
}