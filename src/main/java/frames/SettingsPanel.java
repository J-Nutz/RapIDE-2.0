package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import javax.swing.*;
import java.awt.*;

import static frames.MainFrame.showPanel;

public class SettingsPanel extends JPanel
{
    private final JPanel bottomPanel = new JPanel(new FlowLayout());
    private final JButton homeBtn = new JButton("Back To Home");
    private final JButton logoutBtn = new JButton("Log Out");

    public SettingsPanel()
    {
        //Settings Panel
        setLayout(new BorderLayout());
        setBackground(Color.gray);

        //BottomPanel
        bottomPanel.setBackground(Color.lightGray);

        //HomeBtn
        homeBtn.addActionListener(e -> showPanel("Main"));

        //LogoutBtn
        logoutBtn.addActionListener(e -> showPanel("Login"));

        addComponents();
    }

    private void addComponents()
    {
        bottomPanel.add(homeBtn);
        bottomPanel.add(logoutBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}