package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import logic.utilities.Saver;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.IOException;

import static frames.MainFrame.showPanel;

public class MainPanel extends JPanel
{
    private final JTextPane rapTP = new JTextPane();
    private final JPanel bottomPanel = new JPanel(new FlowLayout());
    private final JButton settingsBtn = new JButton("Settings");
    private final JButton saveBtn = new JButton("Save");
    private final DefaultListModel<String> rhymingWordsDLM = new DefaultListModel<>();
    private JList<String> rhymingWordsList = new JList<>(rhymingWordsDLM);
    private Saver saver = new Saver();

    public MainPanel()
    {
        //MainPanel
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.gray);

        //BottomPanel
        bottomPanel.setBackground(Color.lightGray);

        //SettingsBtn
        settingsBtn.addActionListener(e -> showPanel("Settings"));

        //SaveBtn
        saveBtn.addActionListener(e -> {
            try
            {
                saver.setFileName("TestFile");
                saver.save(rapTP);
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
        });

        //RhymingList
        String[] emptyRhymeList = {"Click", "SomeBtn", "To", "Search", "For", "Rhyming", "Words"};
        for(String word : emptyRhymeList)
        {
            rhymingWordsDLM.addElement(word);
        }

        //RapTP
        StyledDocument styledDocument = rapTP.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        styledDocument.setParagraphAttributes(0, styledDocument.getLength(), center, false);

        try
        {
            styledDocument.insertString(styledDocument.getLength(), "Title", center);
        }
        catch(BadLocationException e)
        {
            e.printStackTrace();
        }

        addComponents();
    }

    private void addComponents()
    {
        //Bottom Panel
        bottomPanel.add(saveBtn);
        bottomPanel.add(settingsBtn);

        //Main Panel
        add(rapTP, BorderLayout.CENTER);
        add(rhymingWordsList, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}