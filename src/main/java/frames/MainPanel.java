package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import javax.swing.*;
import java.awt.*;

import static frames.MainFrame.showPanel;

public class MainPanel extends JPanel
{
    private final JTextArea rapTA = new JTextArea();
    private final JPanel bottomPanel = new JPanel();
    private final JButton testBtn = new JButton("Settings");
    private final DefaultListModel<String> rhymingWordsDLM = new DefaultListModel<>();
    private JList<String> rhymingWordsList = new JList<>(rhymingWordsDLM);
    private final String[] emptyRhymeList = {"Click", "SomeBtn", "To", "Search", "For", "Rhyming", "Words"};

    public MainPanel()
    {
        //MainPanel
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.red);

        //BottomPanel
        bottomPanel.setLayout(new FlowLayout());

        //TestBtn
        testBtn.addActionListener(e -> showPanel("Settings"));

        //RhymingList
        for(String word : emptyRhymeList)
        {
            rhymingWordsDLM.addElement(word);
        }


        addComponents();
    }

    public void addComponents()
    {
        //Bottom Panel
        bottomPanel.add(testBtn);

        //Main Panel
        add(rapTA, BorderLayout.CENTER);
        add(rhymingWordsList, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}