package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame
{
    final JFrame mainFrame = new JFrame();
    final static JPanel containerPanel = new JPanel();
    final static CardLayout cardLayout = new CardLayout(5, 5);

    public MainFrame()
    {
        addComponents();

        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setPreferredSize(new Dimension(750, 650));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void addComponents()
    {
        containerPanel.setBackground(Color.blue);
        containerPanel.setLayout(cardLayout);

        MainPanel mainPanel = new MainPanel();
        containerPanel.add(mainPanel, "Main");

        SettingsPanel settingsPanel = new SettingsPanel();
        containerPanel.add(settingsPanel, "Settings");

        cardLayout.show(containerPanel, "Main");

        mainFrame.add(containerPanel);
    }

    public static void showPanel(String panel)
    {
        cardLayout.show(containerPanel, panel);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}