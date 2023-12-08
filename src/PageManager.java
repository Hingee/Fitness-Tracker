package src;

// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JPanel;

public class PageManager {
   private ArrayList<Workout> workouts;
   private ArrayList<Exercise> exercises;
   private CardLayout cardLayout;
   private JPanel cardPanel;
   private Stack<String> history;
   private ArrayList<Page> pages;

   public PageManager(ArrayList<Workout> var1, ArrayList<Exercise> var2) {
      this.workouts = var1;
      this.exercises = var2;
      this.history = new Stack();
      this.pages = new ArrayList();
      HomePage var3 = new HomePage(this.workouts, this);
      WorkoutPage var4 = new WorkoutPage(this.exercises, this);
      ExercisePage var5 = new ExercisePage(this);
      //ProfilePage var6 = new ProfilePage(this.exercises, this.workouts, this);
      this.pages.add(var3);
      this.pages.add(var4);
      this.pages.add(var5);
      //this.pages.add(var6);
      this.cardLayout = new CardLayout();
      this.cardPanel = new JPanel(this.cardLayout);
      this.cardPanel.setPreferredSize(new Dimension(480, 800));
      this.cardPanel.add(var3, "Home");
      this.cardPanel.add(var4, "Workout-Page");
      this.cardPanel.add(var5, "Exercise-page");
      //this.cardPanel.add(var6, "product");
      this.cardLayout.show(this.cardPanel, "Home");
      this.history.push("WorkoutPage");
      this.forward("Home");
   }

   Page getActivePage() {
      Component[] var1 = this.cardPanel.getComponents();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Component var4 = var1[var3];
         if (var4.isVisible()) {
            return (Page)var4;
         }
      }

      System.err.println("PageManager::getActivePage no visible page, terminating app");
      System.exit(0);
      return null;
   }

   JPanel getJPanel() {
      return this.cardPanel;
   }

   Page findPage(String var1) {
      int var2;
      for(var2 = 0; var2 < this.pages.size() && !((Page)this.pages.get(var2)).getName().equalsIgnoreCase(var1); ++var2) {
      }

      return (Page)this.pages.get(var2);
   }

   String lastPage() {
      if (this.history.size() <= 1) {
         return "";
      } else {
         String var1 = (String)this.history.pop();
         String var2 = (String)this.history.peek();
         this.history.push(var1);
         return var2;
      }
   }

   void refresh() {
      String var1 = (String)this.history.peek();
      if (this.history.size() > 1) {
         this.back();
      }

      this.forward(var1);
   }

   private boolean isTopLevelPage(String var1) {
      return var1.contains("Exercises") || var1.equals("Exercise-Page");
   }

   public void back() {
      this.history.pop();
      String var1 = (String)this.history.peek();
      if (this.isTopLevelPage(var1)) {
         for(int var2 = 0; var2 < this.history.size() - 2; ++var2) {
            this.history.remove(var2);
         }
      }

      this.cardLayout.show(this.cardPanel, (String)this.history.peek());
      this.getActivePage().buildPage();
   }

   public void forward(String var1) {
      if (this.history.size() <= 0 || !((String)this.history.peek()).equals(var1)) {
         if (this.isTopLevelPage(var1)) {
            for(int var2 = 0; var2 < this.history.size() - 2; ++var2) {
               this.history.remove(var2);
            }
         }

         this.history.push(var1);
         this.cardLayout.show(this.cardPanel, var1);
         this.getActivePage().buildPage();
      }
   }
}

