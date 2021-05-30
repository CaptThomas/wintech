
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//import Goal;
//import GoalList;
public class Prototype{



  public static ArrayList<Goal> dToArr(JList<Goal> dm){
    //ArrayList<Goal> r = new ArrayList<Goal>();
    int s = dm.getModel().getSize();
    ArrayList<Goal> list = new ArrayList<Goal>(s);
    for (int i = 0; i < s; i++) {
        list.add(dm.getModel().getElementAt(i));
    }
    return list;
  }
  /*
  public DefaultListModel<Goal> arrToD(){

  }
  */
  public static void main(String args[]) throws Exception {

    JFrame frame = new JFrame();
    JButton delete = new JButton("Delete");
    JButton add = new JButton("Add");
    JButton check = new JButton("Check");
    JButton uncheck = new JButton("Uncheck");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    final DefaultListModel<String> model = new DefaultListModel<>();
    final JList<String> list = new JList<>(model);

    GoalList g = new GoalList();
    
    g.add(new Goal(1,"1","1"));
    g.add(new Goal(1,"3","3"));
    g.add(new Goal(1,"1","2"));
    g.add(new Goal(5,"test","test1"));
    g.Save();
    g.Load();
    ArrayList<Goal> Goals = g.getList();
    if (Goals != null) {
        Goals.stream().map((Goal goal) -> {
            String s = String.format("%s for %d minutes |", goal.getName(), goal.getMinutes());
            if (goal.getFinished() == true) {
                s = String.format("%s completed today", s);
            } else {
                s = String.format("%s not yet completed today", s);
            } return s;
        }).forEachOrdered(s -> {
            model.addElement(s);
        });
    }


    panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
    panel.setLayout(new GridLayout(0,1));


    delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        if (index >= 0)
          model.remove(index);
          Object[] a = model.toArray();
          ArrayList<Goal> arr = new ArrayList<Goal>();
          for(Object g:a){
            arr.add((Goal) g);
          }
          g.replace(arr);
          try{
          g.Save();
          }
          catch(Exception e){
            System.out.println("Exception :):):):)");
            e.printStackTrace();
          }

      }

    });
    check.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            ListSelectionModel selmodel = list.getSelectionModel();
            int index = selmodel.getMinSelectionIndex();
            if (index >= 0) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDateTime now = LocalDateTime.now();
               
                Object[] a = model.toArray();
                ArrayList<Goal> arr = new ArrayList<>();
                for(Object goal:a){
                    arr.add((Goal) goal);
                }
                Goal goal = new Goal(arr.get(index).getMinutes(), arr.get(index).getName(), dtf.format(now));
                arr.set(index, goal);
                g.replace(arr);
                try{
                    g.Save();
                }
                catch(Exception e){
                    System.out.println("Exception :):):):)");
                    e.printStackTrace();
                }

            }
                
        }
    });
    uncheck.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            ListSelectionModel selmodel = list.getSelectionModel();
            int index = selmodel.getMinSelectionIndex();
            if (index >= 0) {
               
                Object[] a = model.toArray();
                ArrayList<Goal> arr = new ArrayList<Goal>();
                for(Object g:a){
                    arr.add((Goal) g);
                }
                Goal goal = new Goal(arr.get(index).getMinutes(), arr.get(index).getName(), "-1");
                arr.set(index, goal);
                g.replace(arr);
                try{
                    g.Save();
                }
                catch(Exception e){
                    System.out.println("Exception :):):):)");
                    e.printStackTrace();
                }

            }
                
        }
    });

    leftPanel.add(new JScrollPane(list));
    rightPanel.add(delete);
    rightPanel.add(check);
    rightPanel.add(uncheck);
    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
    leftPanel.add(list);
    panel.add(leftPanel);
    panel.add(rightPanel);

    frame.setSize(350, 250);
    frame.add(panel, BorderLayout.CENTER);
    //frame.add(new JList(data));
    //frame.add(delete);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("GUI");
    frame.pack();
    frame.setVisible(true);





  }
}
