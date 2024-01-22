package src;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StopwatchButton extends JPanel{
    public StopwatchButton(ExercisePage var1, boolean var2) {
      Box var3 = Box.createHorizontalBox();
      Box var4 = Box.createHorizontalBox();
      JButton var5;

      var5 = new JButton("Rest Timer Set");
      var5.addActionListener((var1x) -> {
         var1.start();
      });
      // var5 = new JButton("Rest Timer Rep");
      // var5.addActionListener((var1x) -> {
      //    var1.start();
      // });

      var4.add(var5);
      var3.add(var4);
      this.add(var3);
   }
}
