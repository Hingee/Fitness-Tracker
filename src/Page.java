package src;
// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Page extends JPanel {
   protected String pageName;
   protected PageManager manager;
   protected boolean showBack;

   Page(String var1, PageManager pageMan) {
      this.init();
      this.pageName = var1;
      this.manager = pageMan;
      this.showBack = false;
   }

   public String getName() {
      return this.pageName;
   }

   protected abstract void init();

   public void buildPage() {
      this.removeAll();
      this.setLayout(new BorderLayout());
      this.add(this.getTop(), "First");
      this.add(this.getCenter(), "Center");
      this.add(this.getBottom(), "South");
   }

   protected JPanel getTop() {
      JPanel var1 = new JPanel();
      var1.setLayout(new GridLayout(2, 0));
      var1.add(new JLabel(this.pageName, 0));
      return var1;
   }

   protected abstract JPanel getCenter();

   protected abstract JPanel getBottom();
}