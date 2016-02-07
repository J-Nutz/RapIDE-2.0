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
        //MainPanel
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.red);

        //BottomPanel
        bottomPanel.setLayout(new FlowLayout());

        //TestBtn
        testBtn.addActionListener(e -> showPanel("Settings"));

        addComponents();
    }

    public void addComponents()
    {
        //Bottom Panel
        bottomPanel.add(testBtn);

        //Main Panel
        add(rapTA, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}