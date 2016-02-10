package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

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
    private final DefaultListModel<String> rhymingWordsDLM = new DefaultListModel<>();
    private JList<String> rhymingWordsList = new JList<>(rhymingWordsDLM);

    public MainFrame()
    {
        //ContainerPanel
        containerPanel.setBackground(Color.blue);
        containerPanel.setLayout(cardLayout);

        //MainFrame
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setPreferredSize(new Dimension(750, 650));
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

        //Which Panel To Show
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