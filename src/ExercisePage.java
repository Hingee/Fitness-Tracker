package src;

// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ExercisePage extends Page {
   public static Exercise displayItem;
   public static boolean fromPlayer;

   public ExercisePage(PageManager var3) {
      super("Exercise Page", var3);
   }

   public static void setDisplayItem(Exercise var0, boolean var1) {
      displayItem = var0;
      fromPlayer = var1;
   }

   protected void init() {
   }

   protected JPanel getCenter() {
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

         JLabel var6 = new JLabel("Sets: ");
         var5.add(var6);
         var5.add(Box.createHorizontalGlue());
         var2.add(var5);
         JTextArea var7 = new JTextArea(""+displayItem.getSets());
         var7.setWrapStyleWord(true);
         var7.setLineWrap(true);
         var7.setEditable(false);
         JScrollPane var8 = new JScrollPane(var7);
         var8.setPreferredSize(new Dimension(800, 200));
         var2.add(var8);
         Box var9 = Box.createHorizontalBox();

         JLabel var10 = new JLabel("Reps: "+displayItem.getRepsPerSet());
         var9.add(var10);
         var9.add(Box.createHorizontalGlue());
         var2.add(var9);
         
         JLabel var11 = new JLabel("Rest between reps: "+displayItem.getRestMinRep());
         var10.add(var11);
         var10.add(Box.createHorizontalGlue());
         var2.add(var10);

         JLabel var12 = new JLabel("Rest between sets: "+displayItem.getRestMinSet());
         var11.add(var12);
         var11.add(Box.createHorizontalGlue());
         var2.add(var11);

         JLabel var13 = new JLabel("Difficulty: "+displayItem.getDifficulty());
         var12.add(var13);
         var12.add(Box.createHorizontalGlue());
         var2.add(var12);

         var1.add(var2);
         return var1;
      }
   }

   public JPanel getBottom() {
      JPanel var1 = new JPanel(new GridLayout(2, 0));
      return var1;
   }
}