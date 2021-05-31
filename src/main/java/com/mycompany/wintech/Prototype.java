
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.Timer;
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
    waterCounter w = new waterCounter();




      Checks checks = new Checks(0);

    JFrame frame = new JFrame();
    JButton delete = new JButton("Delete");
    JButton add = new JButton("Add");
    JButton check = new JButton("Check");
    JButton uncheck = new JButton("Uncheck");
    JButton suggest = new JButton("Suggest");
    JPanel panel = new JPanel();

    JButton addcup = new JButton("Add Cup");
    JButton reset = new JButton("Reset");
    JLabel cups = new JLabel("Cups of Water drunk, try to get at least 8 per day!");
    JLabel cups1 = new JLabel("0");

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel lastPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
    lastPanel.setLayout(new BoxLayout(lastPanel, BoxLayout.Y_AXIS));








    final DefaultListModel<String> model = new DefaultListModel<>();
    final JList<String> list = new JList<>(model);

    GoalList g = new GoalList();
    g.LoadChecked();
    checks = g.getCheck();

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
      "Minutes per day", time,
      "Name", name
    };






    panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
    panel.setLayout(new GridLayout(0,1));


    ActionListener timeWarn = new ActionListener(){
      public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(frame,"Take a Break.");

      }
    };
    new Timer(600000, timeWarn).start();

    addcup.addActionListener((ActionEvent event) -> {
      w.drink();
      cups1.setText(String.valueOf(w.getCups()));
    });
    reset.addActionListener((ActionEvent event) -> {
      w.reset();
      cups1.setText(String.valueOf(w.getCups()));      
    });


    suggest.addActionListener((ActionEvent event) -> {
      String[] Quotes = {
        "Stretch for 10 minutes",
        "Take a breathing break (2min)",
        "Take a walk or run (10-30min depending on activity level)",
        "Get some water, make sure to drink over 8 cups per day",
        "Meditate (2 minutes)",
        "Get a chore done",
        "Finish a task you've been putting off"

      };
      int quote =   (int) (Math.random() * Quotes.length);

      String s = Quotes[quote];
          JOptionPane.showMessageDialog(frame, s);
    });

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
                int numcheck = g.getCheck().getChecks();
                numcheck++;
                g.replaceChecks(new Checks(numcheck));
                if (numcheck % 5 == 0) {
                    String[] Quotes = {
        "WOW! Nice job completing your goal! Keep it up, \"Opportunity does not knock, it presents itself when you beat down the door.\" - Kyle Chandler",
        "You're finished with your goal already? Impressive! Just remember the wise words of our genie, Will Smith, \"Don\'t let success go to your head and failure to your heart.\" But... since you did such a good job, I\'ll let it slide.",
        "Have you finished your goal yet? Hmm.. you did? That took longer than expected, but remember, \"A diamond is merely a lump of coal that did well under pressure.\"",
        "That goal took a lonnggg time, but, \"Only those who attempts the absurd are capable of achieving the impossible.\" - Miguel de Unamuno",
        "You completed your goal? That\'s amazing! Keep going! \"Talent hits a target no one else can hit. Genius hits a target no one else can see.\"- Arthur Schopenhauer",
        "You took your time with that goal, but that\'s Ok! \"Success is a slow process, quitting won\'t speed it up.\"",
        "Keep working hard and you\'ll be sure to achieve the rest of your goals, but keep in mind \"What you get by achieving your goals is as important as what you become by achieving your goals.\"- Henry David Thoreau",
        "You completed another goal! How cool! Just as cool as how, \"Nothing is impossible because it says i\'m possible!\"- Audrey Hepburn Let\'s remember that for setting our next goal.",
        "As Steven Wright said, \"If at first you don\'t succeed, then skydiving definitely isn\'t for you.\" Hmmmm...let\'s complete another goal instead!",
        "Andddd... Yes! You completed another goal for today! Let\'s do this again tomorrow because, remember, \"The secret of your success is found in your daily routines.\""

      };
      int quote =   (int) (Math.random() * Quotes.length);

      String s = Quotes[quote];
          JOptionPane.showMessageDialog(frame, s);
                }

            }
            try{
                g.Save();
                g.SaveChecked();
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


    topPanel.add(new JScrollPane(list));
    bottomPanel.add(delete);
    bottomPanel.add(add);
    bottomPanel.add(check);
    bottomPanel.add(uncheck);
    bottomPanel.add(suggest);

    lastPanel.add(addcup);
    lastPanel.add(reset);
    lastPanel.add(cups);
    lastPanel.add(cups1);
    //bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

    topPanel.add(list);
    panel.add(topPanel);
    panel.add(bottomPanel);
    panel.add(lastPanel);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setPreferredSize(screenSize);
    frame.setSize(350, 250);
    panel.setBackground(new Color(244, 241, 188));
    topPanel.setBackground(new Color(244, 241, 188));
    list.setBackground(new Color(244, 241, 188));
    bottomPanel.setBackground(new Color(244, 241, 188));
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
