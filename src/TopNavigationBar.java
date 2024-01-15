package src;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TopNavigationBar extends JPanel {
   public TopNavigationBar(PageManager var1, boolean var2) {
      Box var3 = Box.createHorizontalBox();
      Box var4 = Box.createHorizontalBox();
      JButton var5;

      var5 = new JButton("Analytics");
      var5.addActionListener((var1x) -> {
         var1.forward("analytics-page");
      });
      JButton var6 = new JButton("Scheduler");
      var6.addActionListener((var1x) -> {
         var1.forward("scheduler-page");
      });
      JButton var7 = new JButton("Profile");
      var7.addActionListener((var1x) -> {
         var1.forward("profile-page");
      });
      JButton var8 = new JButton("Other Users");
      var8.addActionListener((var1x) -> {
         var1.forward("otherUsers-page");
      });
      JButton var9 = new JButton("Home");
      var9.addActionListener((var1x) -> {
         var1.forward("home-page");
      });
      var4.add(var9);
      var4.add(var5);
      var4.add(var6);
      var4.add(var7);
      var4.add(var8);
      var3.add(var4);
      this.add(var3);
   }
}