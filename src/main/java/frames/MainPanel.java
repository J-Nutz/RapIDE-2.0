package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import javax.swing.*;
import java.awt.*;

import static frames.MainFrame.showPanel;

public class MainPanel extends JPanel
{
    final JTextArea rapTA = new JTextArea();
    final JPanel bottomPanel = new JPanel();
    final JButton testBtn = new JButton("Settings");

    public MainPanel()
    {
        addComponents();

        setBackground(Color.red);
    }

    public void addComponents()
    {
        setLayout(new BorderLayout(5, 5));
        add(rapTA, BorderLayout.CENTER);

        testBtn.addActionListener(e -> showPanel("Settings"));

        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(testBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}