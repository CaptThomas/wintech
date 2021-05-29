import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

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
  public static void main(String args[]) {
    String[] data = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

    JFrame frame = new JFrame();
    JButton delete = new JButton("Delete");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    final DefaultListModel<String> model = new DefaultListModel<>();
    final JList<String> list = new JList<>(model);
    model.addElement("John Smith");
    model.addElement("Kathy Green");


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

    rightPanel.add(delete);
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
