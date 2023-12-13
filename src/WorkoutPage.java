package src;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WorkoutPage extends Page {
   public static Workout displayItem = null;
   public boolean fromPlayer;
   private ArrayList<Exercise> dataSource;

   public WorkoutPage(PageManager pageMan) {
      super("", pageMan);
      this.manager = pageMan;
      this.pageName = "Workout";
      dataSource = DataSource.getExercises();
      this.buildPage();
   }

   protected void init() {
   }

   protected JPanel getTop() {
      JPanel var1 = new JPanel(new GridLayout(6, 0));
      var1.add(new JLabel(this.pageName, 0));
      JPanel var2 = new JPanel();

      var1.add(var2);
      JPanel var3 = new JPanel();
      var1.add(var3);

      JPanel var5 = new JPanel(new BorderLayout());
      var5.add(var1, "Center");
      return var5;
   }

   protected JPanel getCenter() {
      JPanel var1 = new JPanel();
      var1.setLayout(new BorderLayout());

      if(displayItem!=null) {
         dataSource = displayItem.getExercises();
      }
      this.dataSource.sort((var0, var1x) -> {
          return var0.getName().compareToIgnoreCase(var1x.getName());
      });
      String[][] var3 = new String[this.dataSource.size()][6];

      for(int var4 = 0; var4 < var3.length; ++var4) {
          Exercise var5 = (Exercise)this.dataSource.get(var4);
          var3[var4][0] = var5.getName();
          var3[var4][1] = ""+var5.getSets();
          var3[var4][2] = "" + var5.getRepsPerSet();
          var3[var4][3] = "" + var5.getRestMinRep()+" mins";
          var3[var4][4] = "" + var5.getRestMinSet()+" mins";
          var3[var4][5] = var5.getDifficulty();
      }

      String[] var8 = new String[]{"Name", "Sets", "Reps", "Rest Between Reps", "Rest Between Sets", "Difficulty"};
      DefaultTableModel var9 = new DefaultTableModel(var3, var8);
      JTable var6 = new JTable(var9);
      var6.getSelectionModel().addListSelectionListener((var2x) -> {
          if (!var2x.getValueIsAdjusting()) {
              int var = var6.getSelectedRow();
              int var4 = var6.getSelectedColumn();
              Exercise var5;
          if (var >= 0 && var < this.dataSource.size() && var4 < 4) {
             var5 = (Exercise)this.dataSource.get(var);
             this.goToExercisePage(var5);
          }

          if (var4 == 4) {
             var5 = (Exercise)this.dataSource.get(var);
          }
      }});

      JScrollPane var7 = new JScrollPane(var6);
      var1.add(var7, "Center");
      this.add(var1, "South");
      return var1;
  }

  private void goToExercisePage(Exercise var5) {
      ExercisePage.setDisplayItem(var5, true);
      this.manager.forward("Exercise-page");
  }

  protected JPanel getBottom() {
      JPanel var1 = new JPanel();
      String var2 = String.format("");
      JLabel var3 = new JLabel(var2);
      var1.add(var3);
      return var1;
  }

   public static void setDisplayItem(Workout var5) {
      displayItem = var5;
   }
}
