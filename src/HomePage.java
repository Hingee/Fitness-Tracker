package src;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HomePage extends Page {
   private ArrayList<Workout> dataSource;

   public HomePage(PageManager pageMan) {
      super("Home", pageMan);
      dataSource = DataSource.getWorkouts();
   }

   protected void init() {
   }

    protected JPanel getCenter() {
        JPanel var1 = new JPanel();
        var1.setLayout(new BorderLayout());
        this.dataSource.sort((var0, var1x) -> {
            return var0.getName().compareToIgnoreCase(var1x.getName());
        });
        String[][] var3 = new String[this.dataSource.size()][3];

        for(int var4 = 0; var4 < var3.length; ++var4) {
            Workout var5 = (Workout)this.dataSource.get(var4);
            var3[var4][0] = var5.getName();
            var3[var4][1] = var5.getDescription();
            var3[var4][2] = "" + var5.getEst()+" mins";
        }

        String[] var8 = new String[]{"Name", "Description", "Estimated Time"};
        DefaultTableModel var9 = new DefaultTableModel(var3, var8);
        JTable var6 = new JTable(var9);
        var6.getSelectionModel().addListSelectionListener((var2x) -> {
            if (!var2x.getValueIsAdjusting()) {
                int var = var6.getSelectedRow();
                int var4 = var6.getSelectedColumn();
                Workout var5;
            if (var >= 0 && var < this.dataSource.size() && var4 < 4) {
               var5 = (Workout)this.dataSource.get(var);
               this.goToWorkoutPage(var5);
            }

            if (var4 == 4) {
               var5 = (Workout)this.dataSource.get(var);
            }
        }});

        JScrollPane var7 = new JScrollPane(var6);
        var1.add(var7, "Center");
        this.add(var1, "South");
        return var1;
    }

    private void goToWorkoutPage(Workout var5) {
        WorkoutPage.setDisplayItem(var5);
        this.manager.forward("Workout-Page");
    }

    protected JPanel getBottom() {
        JPanel var1 = new JPanel();
        String var2 = String.format("");
        JLabel var3 = new JLabel(var2);
        var1.add(var3);
        return var1;
    }
}