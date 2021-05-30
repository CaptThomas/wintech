
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.*;
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
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    final DefaultListModel<Goal> model = new DefaultListModel<>();
    final JList<Goal> list = new JList<>(model);

    GoalList g = new GoalList();
    /*
    g.add(new Goal(1,"1","1"));
    g.add(new Goal(1,"3","3"));
    g.add(new Goal(1,"1","2"));
    g.add(new Goal(5,"test","test1"));*/
    //g.Save();
    g.Load();
    for(Goal i: g.getList()){
      model.addElement(i);
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


    delete.addActionListener(new ActionListener(){
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

    add.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event){
        int result = JOptionPane.showConfirmDialog(null, test,
                 "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          model.addElement(new Goal(Integer.parseInt(time.getText()),name.getText(),""));
        }
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


    leftPanel.add(new JScrollPane(list));
    rightPanel.add(delete);
    rightPanel.add(add);
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
