package src;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExercisePage extends Page {
   public static Exercise displayItem;
   public static boolean fromPlayer;

   Box var3 = Box.createHorizontalBox();
   Box var4 = Box.createHorizontalBox();
   JButton var5;

   public ExercisePage(PageManager var3) {
      super("Exercise Page", var3);
      var5 = new JButton("Analytics");
   }

   public static void setDisplayItem(Exercise var0, boolean var1) {
      displayItem = var0;
      fromPlayer = var1;
   }

   protected void init() {
   }

   protected JPanel getCenter() {
      JPanel stopWatch = new JPanel();
      stopWatch.setLayout(new GridLayout(1, 0));
      stopWatch.add(new StopwatchButton(this, this.showBack));

      if (displayItem == null) {
         return new JPanel();
      } else {
         JPanel var1 = new JPanel();
         var1.setLayout(new BorderLayout());
         Box var2 = Box.createVerticalBox();
         Box var3 = Box.createHorizontalBox();

         JLabel var4 = new JLabel("Exercise Name: " + displayItem.getName());
         var3.add(var4);
         var3.add(Box.createHorizontalGlue());
         var2.add(var3);
         Box var5 = Box.createHorizontalBox();

         JLabel var6 = new JLabel("Sets: "+displayItem.getSets());
         var5.add(var6);
         var5.add(Box.createHorizontalGlue());
         var2.add(var5);
         
         JLabel var10 = new JLabel("Reps: "+displayItem.getRepsPerSet());
         var6.add(var10);
         var6.add(Box.createHorizontalGlue());
         var2.add(var6);
         
         float restRep = displayItem.getRestMinRep();
         String restResult;
         restResult = checkSecs(restRep);
         JLabel var11 = new JLabel("Rest between reps: "+restResult);
         var10.add(var11);
         var10.add(Box.createHorizontalGlue());
         var2.add(var10);

         float restSet = displayItem.getRestMinSet();
         restResult = checkSecs(restSet);
         JLabel var12 = new JLabel("Rest between sets: "+restResult);
         var11.add(var12);
         var11.add(Box.createHorizontalGlue());
         var2.add(var11);

         JLabel var13 = new JLabel("Difficulty: "+displayItem.getDifficulty());
         var12.add(var13);
         var12.add(Box.createHorizontalGlue());
         var2.add(var12);

         var1.add(var2);
         var2.add(stopWatch);
         return var1;
      }
   }

   public void start() {

   }
}