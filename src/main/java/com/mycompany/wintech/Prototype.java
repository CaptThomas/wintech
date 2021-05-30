
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.util.ArrayList;
//import Goal;
//import GoalList;
public class Prototype{



  public static ArrayList<Goal> dToArr(JList<Goal> dm){
    //ArrayList<Goal> r = new ArrayList<Goal>();
    int s = dm.getModel().getSize();
    ArrayList<Goal> list = new ArrayList<>(s);
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

    /*g.add(new Goal(1,"1","1"));
    g.add(new Goal(1,"3","3"));
    g.add(new Goal(1,"1","2"));
    g.add(new Goal(5,"test","test1"));
    g.Save();*/
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


    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("x:"));
    myPanel.add(xField);
    myPanel.add(new JLabel("y:"));
    myPanel.add(yField);


    JTextField time = new JTextField(5);
    JTextField name = new JTextField(5);

    Object[] test = {
      "Time", time,
      "Name", name
    };





    panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
    panel.setLayout(new GridLayout(0,1));


    delete.addActionListener((ActionEvent event) -> {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        if (index >= 0)
            model.remove(index);
        ArrayList<Goal> arr =g.getList();
        arr.remove(index);
        g.replace(arr);
        try{
            g.Save();
        }
        catch(Exception e){
            System.out.println("Exception :):):):)");
        }
    });
    check.addActionListener((ActionEvent event) -> {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        if (index >= 0) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            
            ArrayList<Goal> arr = g.getList();
            
            Goal goal = new Goal(arr.get(index).getMinutes(), arr.get(index).getName(), dtf.format(now));
            arr.set(index, goal);
            g.replace(arr);
            model.clear();
            if (arr != null) {
                arr.stream().map((Goal newgoals) -> {
                    String s = String.format("%s for %d minutes |", newgoals.getName(), newgoals.getMinutes());
                    if (newgoals.getFinished() == true) {
                        s = String.format("%s completed today", s);
                    } else {
                        s = String.format("%s not yet completed today", s);
                    } return s;
                }).forEachOrdered(s -> {
                    model.addElement(s);
                });
            }
            try{
                g.Save();
            }
            catch(Exception e){
                System.out.println("Exception :):):):)");
            }
            
        }
    });
    uncheck.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            ListSelectionModel selmodel = list.getSelectionModel();
            int index = selmodel.getMinSelectionIndex();
            if (index >= 0) {
                
                
                ArrayList<Goal> arr = g.getList();
                
                Goal goal = new Goal(arr.get(index).getMinutes(), arr.get(index).getName(), "-1");
                arr.set(index, goal);
                g.replace(arr);
                model.clear();
                if (arr != null) {
                    arr.stream().map((Goal newgoals) -> {
                        String s = String.format("%s for %d minutes |", newgoals.getName(), newgoals.getMinutes());
                        if (newgoals.getFinished() == true) {
                            s = String.format("%s completed today", s);
                        } else {
                            s = String.format("%s not yet completed today", s);
                        } return s;
                    }).forEachOrdered(s -> {
                        model.addElement(s);
                    });
                }
                try{
                    g.Save();
                }
                catch(Exception e){
                    System.out.println("Exception :):):):)");
                }
                
            }
        }
    });

    add.addActionListener((ActionEvent event) -> {
        int result = JOptionPane.showConfirmDialog(null, test,
                "Enter Time and Name", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            ArrayList<Goal> arr = g.getList();
            
            arr.add(new Goal (Integer.parseInt(time.getText()),name.getText(),"-1"));
            g.replace(arr);
            model.clear();
            if (arr != null) {
                arr.stream().map((Goal newgoals) -> {
                    String s = String.format("%s for %d minutes |", newgoals.getName(), newgoals.getMinutes());
                    if (newgoals.getFinished() == true) {
                        s = String.format("%s completed today", s);
                    } else {
                        s = String.format("%s not yet completed today", s);
                    } return s;
                }).forEachOrdered(s -> {
                    model.addElement(s);
                });
            }
            try{
                g.Save();
            }
            catch(Exception e){
                System.out.println("Exception :):):):)");
            }
            
            
        }
    });


    leftPanel.add(new JScrollPane(list));
    rightPanel.add(delete);
    rightPanel.add(add);
    rightPanel.add(check);
    rightPanel.add(uncheck);
    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

    leftPanel.add(list);
    panel.add(leftPanel);
    panel.add(rightPanel);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setPreferredSize(screenSize);
    frame.setSize(350, 250);
    Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
    frame.setIconImage(icon);
    frame.add(panel, BorderLayout.CENTER);
    //frame.add(new JList(data));
    //frame.add(delete);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Grounded");
    frame.pack();
    frame.setVisible(true);

  }
}
