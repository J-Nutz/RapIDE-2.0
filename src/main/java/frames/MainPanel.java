package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import local.Strings;
import logic.utilities.FileUtils;
import logic.utilities.Saver;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;

import static frames.MainFrame.showPanel;

public class MainPanel extends JPanel
{
    private final JPanel topPanel = new JPanel(new FlowLayout());
    private final JPanel bottomPanel = new JPanel(new FlowLayout());
    private final JPanel rightPanel = new JPanel();

    private final JTextPane rapTP = new JTextPane();
    private final JTextField titleTF = new JTextField("Enter Title");

    private final JComboBox<String> saveFilesCB = new JComboBox<>();
    private final JComboBox<String> deleteFilesCB = new JComboBox<>();

    private final JButton insertBtn = new JButton("Insert");
    private final JButton saveBtn = new JButton("Save");
    private final JButton openBtn = new JButton("Open File");
    private final JButton deleteBtn = new JButton("Delete Files");
    private final JButton settingsBtn = new JButton("Settings");
    private final JButton cancelBtn = new JButton("Cancel");

    private final DefaultListModel<String> rhymingWordsDLM = new DefaultListModel<>();
    private JList<String> rhymingWordsList = new JList<>(rhymingWordsDLM);

    private final JScrollPane rlScrollPane = new JScrollPane(rhymingWordsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private Saver saver = new Saver();
    private FileUtils fileUtils = new FileUtils();

    final boolean[] deleteSavesShowing = {false};
    final boolean[] savesShowing = {false};

    public MainPanel()
    {
        //MainPanel
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.gray);

        //TopPanel
        topPanel.setBackground(Color.lightGray);

        //RightPanel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(100, this.getHeight()));

        //TitleTF2
        titleTF.setHorizontalAlignment(JTextField.CENTER);
        titleTF.setPreferredSize(new Dimension(150, 40));
        titleTF.setBorder(null);
        titleTF.setFont(new Font("Arial", Font.PLAIN, 22));

        //BottomPanel
        bottomPanel.setBackground(Color.lightGray);

        //SaveFilesCB
        fileUtils.getFiles(saveFilesCB, Strings.savesDir);
        saveFilesCB.setVisible(false);

        //DeleteFilesCB
        fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
        deleteFilesCB.setVisible(false);

        //RlScrollPane
        rlScrollPane.setBorder(null);
        rlScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        //InsertBtn
        insertBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertBtn.addActionListener(e ->
        {
            StyledDocument styledDocument = rapTP.getStyledDocument();
            try
            {
                styledDocument.insertString(rapTP.getCaretPosition(), rhymingWordsList.getSelectedValue(), null);
            }
            catch(BadLocationException e1)
            {
                e1.printStackTrace();
            }

            rapTP.requestFocus();
        });

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
        openBtn.addActionListener(e ->
        {
            resetDeleteCB();

            if(!setShowing(savesShowing, openBtn, saveFilesCB, "Open File", "Select"))
            {
                fileUtils.loadFile(rapTP, titleTF, Strings.savesDir, saveFilesCB.getSelectedItem().toString());
            }
        });

        //DeleteBtn
        deleteBtn.addActionListener(e1 ->
        {
            resetOpenCB();

            if(!setShowing(deleteSavesShowing, deleteBtn, deleteFilesCB, "Delete Files", "Delete"))
            {
                fileUtils.deleteFile(rapTP, titleTF, Strings.savesDir, deleteFilesCB.getSelectedItem().toString());
                fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
                fileUtils.getFiles(saveFilesCB, Strings.savesDir);
            }
        });

        //CancelBtn
        cancelBtn.setVisible(false);

        cancelBtn.addActionListener(e ->
        {
            resetOpenCB();
            resetDeleteCB();
            cancelBtn.setVisible(false);
        });

        //SettingsBtn
        settingsBtn.addActionListener(e -> showPanel("Settings"));

        //RhymingWordsList
        rhymingWordsList.setFixedCellHeight(20);
        rhymingWordsList.setFixedCellWidth(100);
        rhymingWordsList.setAlignmentX(Component.CENTER_ALIGNMENT);
        rhymingWordsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String[] emptyRhymeList = {"Click", "SomeBtn", "To", "Search", "For", "Rhyming", "Words"};
        for(String word : emptyRhymeList)
        {
            rhymingWordsDLM.addElement(word);
        }

        addComponents();
    }

    private boolean setShowing(boolean[] showing, JButton button, JComboBox<String> comboBox, String message, String message2)
    {
        if(showing[0] && button.getText().equals(message2))
        {
            showing[0] = false;
            button.setText(message);
            comboBox.setVisible(showing[0]);
            cancelBtn.setVisible(false);
            return false;
        }
        else
        {
            showing[0] = true;
            button.setText(message2);
            comboBox.setVisible(showing[0]);
            cancelBtn.setVisible(true);
            return true;
        }
    }

    private void resetDeleteCB()
    {
        deleteFilesCB.setVisible(false);
        deleteBtn.setText("Delete Files");
        deleteSavesShowing[0] = false;

    }

    private void resetOpenCB()
    {
        saveFilesCB.setVisible(false);
        openBtn.setText("Open File");
        savesShowing[0] = false;
    }

    private void addComponents()
    {
        //TopPanel
        topPanel.add(titleTF);

        //RightPanel
        rightPanel.add(rlScrollPane);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(insertBtn);

        //Bottom Panel
        bottomPanel.add(saveBtn);
        bottomPanel.add(saveFilesCB);
        bottomPanel.add(openBtn);
        bottomPanel.add(deleteFilesCB);
        bottomPanel.add(deleteBtn);
        bottomPanel.add(settingsBtn);
        bottomPanel.add(cancelBtn);

        //Main Panel
        add(topPanel, BorderLayout.NORTH);
        add(rapTP, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}