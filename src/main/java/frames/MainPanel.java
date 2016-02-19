package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import local.Strings;
import logic.utilities.FileUtils;
import logic.utilities.Saver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static frames.MainFrame.showPanel;

public class MainPanel extends JPanel
{
    private final JPanel topPanel = new JPanel(new FlowLayout());
    private final JPanel bottomPanel = new JPanel(new FlowLayout());

    private final JTextPane rapTP = new JTextPane();
    private final JTextField titleTF = new JTextField("Enter Title");

    private final JComboBox<String> saveFilesCB = new JComboBox<>();
    private final JComboBox<String> deleteFilesCB = new JComboBox<>();

    private final JButton saveBtn = new JButton("Save");
    private final JButton openBtn = new JButton("Open File");
    private final JButton deleteBtn = new JButton("Delete File");
    private final JButton settingsBtn = new JButton("Settings");

    private final DefaultListModel<String> rhymingWordsDLM = new DefaultListModel<>();
    private JList<String> rhymingWordsList = new JList<>(rhymingWordsDLM);

    private Saver saver = new Saver();
    private FileUtils fileUtils = new FileUtils();

    public MainPanel()
    {
        //MainPanel
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.gray);

        //TopPanel
        topPanel.setBackground(Color.lightGray);

        //TitleTF2
        titleTF.setHorizontalAlignment(JTextField.CENTER);
        titleTF.setPreferredSize(new Dimension(150, 40));
        titleTF.setBorder(null);

        //BottomPanel
        bottomPanel.setBackground(Color.lightGray);

        //SaveFilesCB
        fileUtils.getFiles(saveFilesCB, Strings.savesDir);
        saveFilesCB.setVisible(false);

        //DeleteFilesCB
        fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
        deleteFilesCB.setVisible(false);

        //SaveBtn
        saveBtn.addActionListener(e ->
        {
            try
            {
                saver.setFileName(titleTF.getText());
                saver.save(rapTP, titleTF);
                System.out.println("Saving: " + titleTF.getText());
                fileUtils.getFiles(saveFilesCB, Strings.savesDir);
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
        });

        //OpenBtn
        final boolean[] savesShowing = {false};

        openBtn.addActionListener(e ->
        {
            if(!savesShowing[0])
            {
                openBtn.setText("Select");
                saveFilesCB.setVisible(true);
                savesShowing[0] = true;
            }
            else
            {
                openBtn.setText("Open File");
                fileUtils.loadFile(rapTP, titleTF, Strings.savesDir, saveFilesCB.getSelectedItem().toString());
                saveFilesCB.setVisible(false);
                savesShowing[0] = false;
            }
        });

        //DeleteBtn
        final boolean[] deleteSavesShowing = {false};

        deleteBtn.addActionListener(e1 ->
        {
            if(!deleteSavesShowing[0])
            {
                deleteBtn.setText("Delete");
                deleteFilesCB.setVisible(true);
                deleteSavesShowing[0] = true;
            }
            else
            {
                fileUtils.deleteFile(rapTP, titleTF, Strings.savesDir, deleteFilesCB.getSelectedItem().toString());
                fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
                fileUtils.getFiles(saveFilesCB, Strings.savesDir);
                deleteBtn.setText("Delete File");
                deleteFilesCB.setVisible(false);
                deleteSavesShowing[0] = false;
            }
        });

        //SettingsBtn
        settingsBtn.addActionListener(e -> showPanel("Settings"));

        //RhymingWordsList
        String[] emptyRhymeList = {"Click", "SomeBtn", "To", "Search", "For", "Rhyming", "Words"};
        for(String word : emptyRhymeList)
        {
            rhymingWordsDLM.addElement(word);
        }

        addComponents();
    }

    private void addComponents()
    {
        //TopPanel
        topPanel.add(titleTF);

        //Bottom Panel
        bottomPanel.add(saveBtn);
        bottomPanel.add(saveFilesCB);
        bottomPanel.add(openBtn);
        bottomPanel.add(deleteFilesCB);
        bottomPanel.add(deleteBtn);
        bottomPanel.add(settingsBtn);

        //Main Panel
        add(topPanel, BorderLayout.NORTH);
        add(rapTP, BorderLayout.CENTER);
        add(rhymingWordsList, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}