package com.mycompany.wintech;



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
public class TestFrame{
  public static void main(String args[]) throws Exception {

    final DefaultListModel<String> model = new DefaultListModel<>();
    final JList<String> list = new JList<>(model);
    GoalList a = new GoalList();





    JFrame frame = new JFrame();
    JButton delete = new JButton("Delete");
    JButton check = new JButton("Check");
    JButton uncheck = new JButton("Uncheck");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


    ArrayList<Goal> Goals = a.getList();
    if (Goals != null) {
        Goals.stream().map((Goal g) -> {
            String s = String.format("%s for %d minutes |", g.getName(), g.getMinutes());
            if (g.getFinished() == true) {
                s = String.format("%s completed today", s);
            } else {
                s = String.format("%s not yet completed today", s);
            } return s;
        }).forEachOrdered(s -> {
            model.addElement(s);
        });
    } else {
    }


    panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
    panel.setLayout(new GridLayout(0,1));

    leftPanel.add(new JScrollPane(list));
    delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        if (index >= 0)
          model.remove(index);
      }

    });
    check.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            ListSelectionModel selmodel = list.getSelectionModel();
            int index = selmodel.getMinSelectionIndex();
            if (index >= 0) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDateTime now = LocalDateTime.now();
                Goal g = new Goal(Goals.get(index).getMinutes(), Goals.get(index).getName(), dtf.format(now));
                Goals.set(index, g);
                GoalList.replace(Goals);
            }

        }
    });
    uncheck.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            ListSelectionModel selmodel = list.getSelectionModel();
            int index = selmodel.getMinSelectionIndex();
            if (index >= 0) {
                Goal g = new Goal(Goals.get(index).getMinutes(), Goals.get(index).getName(), "-1");
                Goals.set(index, g);
                GoalList.replace(Goals);
            }

        }
    });

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
