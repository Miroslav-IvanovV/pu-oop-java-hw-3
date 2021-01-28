package UI;

import javax.swing.*;

public class Modal extends JDialog {

    public Modal(JFrame parent, String title, String message){
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
