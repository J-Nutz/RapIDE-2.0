package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import logic.utilities.FileUtils;

import javax.swing.*;
import java.awt.*;

public class MainFrame
{
    private final JFrame mainFrame = new JFrame();
    private final static JPanel containerPanel = new JPanel();
    private final static CardLayout cardLayout = new CardLayout(5, 5);
    private final EntrancePanel entrancePanel = new EntrancePanel();
    private final MainPanel mainPanel = new MainPanel();
    private final SettingsPanel settingsPanel = new SettingsPanel();

    public MainFrame()
    {
        FileUtils fileUtils = new FileUtils();
        fileUtils.createBaseFolders();

        //ContainerPanel
        containerPanel.setBackground(Color.gray);
        containerPanel.setLayout(cardLayout);

        //MainFrame
        mainFrame.setBackground(Color.gray);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);

        addComponents();
    }

    private void addComponents()
    {
        //ContainerPanel
        containerPanel.add(entrancePanel, "Login");
        containerPanel.add(mainPanel, "Main");
        containerPanel.add(settingsPanel, "Settings");

        //MainFrame
        mainFrame.add(containerPanel);

        //Which View To Show
        cardLayout.show(containerPanel, "Login");
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