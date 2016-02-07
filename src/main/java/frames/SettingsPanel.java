package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import javax.swing.*;
import java.awt.*;

import static frames.MainFrame.showPanel;

public class SettingsPanel extends JPanel
{
    private final JButton homeBtn = new JButton("Back To Home");

    public SettingsPanel()
    {
        setBackground(Color.pink);
        homeBtn.addActionListener(e -> showPanel("Main"));

        addComponents();
    }

    private void addComponents()
    {
        add(homeBtn);
    }
}