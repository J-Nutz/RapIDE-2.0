package frames;

/*
 * Created by Jonah on 2/5/2016.
 */

import global.Strings;
import logic.utilities.APIUtils;
import logic.utilities.FileUtils;
import logic.utilities.Saver;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static frames.MainFrame.showPanel;

public class MainPanel extends JPanel
{
    private final JPanel topPanel = new JPanel(new FlowLayout());
    private final JPanel bottomPanel = new JPanel(new FlowLayout());
    private final JPanel rightPanel = new JPanel();

    private final JTextPane rapTP = new JTextPane();
    private final JTextField titleTF = new JTextField("Enter Title");
    private final JTextField searchTF = new JTextField("Enter Word");
    private final JTextField newNameTF = new JTextField("Enter Name");

    private final JComboBox<String> saveFilesCB = new JComboBox<>();
    private final JComboBox<String> deleteFilesCB = new JComboBox<>();
    private final JComboBox<String> renameFilesCB = new JComboBox<>();

    private final JButton insertBtn = new JButton("Insert");
    private final JButton clearBtn = new JButton("Clear");
    private final JButton createBtn = new JButton("Create");
    private final JButton saveBtn = new JButton("Save");
    private final JButton openBtn = new JButton("Open File");
    private final JButton deleteBtn = new JButton("Delete Files");
    private final JButton renameBtn = new JButton("Rename Files");
    private final JButton settingsBtn = new JButton("Settings");
    private final JButton searchBtn = new JButton("Rhyming Words");
    private final JButton cancelBtn = new JButton("Cancel");

    private final DefaultListModel<String> rhymingWordsDLM = new DefaultListModel<>();
    private JList<String> rhymingWordsList = new JList<>(rhymingWordsDLM);

    private final JScrollPane rlScrollPane = new JScrollPane(rhymingWordsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private Saver saver = new Saver();
    private FileUtils fileUtils = new FileUtils();

    final boolean[] deleteSavesShowing = {false};
    final boolean[] savesShowing = {false};
    final boolean[] searchShowing = {false};
    final boolean[] isFirstClick = {true};
    final int[] timesClicked = {0};

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

        //TitleTF
        titleTF.setHorizontalAlignment(JTextField.CENTER);
        titleTF.setPreferredSize(new Dimension(150, 40));
        titleTF.setBorder(null);
        titleTF.setFont(new Font("Arial", Font.PLAIN, 22));

        titleTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                titleTF.setText("");
            }
        });

        //searchTF
        searchTF.setPreferredSize(new Dimension(90, 24));
        searchTF.setHorizontalAlignment(SwingConstants.CENTER);
        searchTF.setVisible(false);

        searchTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                searchTF.setText("");
            }
        });

        //NewNameTF
        newNameTF.setVisible(false);
        newNameTF.setPreferredSize(new Dimension(90, 24));
        newNameTF.setHorizontalAlignment(SwingConstants.CENTER);

        newNameTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                newNameTF.setText("");
            }
        });

        //BottomPanel
        bottomPanel.setBackground(Color.lightGray);

        //SaveFilesCB
        fileUtils.getFiles(saveFilesCB, Strings.savesDir);
        saveFilesCB.setVisible(false);

        //DeleteFilesCB
        fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
        deleteFilesCB.setVisible(false);

        //renameFilesCB
        fileUtils.getFiles(renameFilesCB, Strings.savesDir);
        renameFilesCB.setVisible(false);

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
                styledDocument.insertString(rapTP.getCaretPosition(), rhymingWordsList.getSelectedValue() + " ", null);
            }
            catch(BadLocationException e1)
            {
                e1.printStackTrace();
            }

            rapTP.requestFocus();
        });

        //ClearBtn
        clearBtn.addActionListener(e -> resetRhymingList());
        clearBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //CreateBtn
        createBtn.addActionListener(e ->
        {
            resetRenameComponents();
            resetDeleteComponents();
            resetLoadComponents();
            resetSearchComponents();

            rapTP.setText("");
            titleTF.setText("Enter Title");
        });

        //SaveBtn
        saveBtn.addActionListener(e ->
        {
            resetRenameComponents();
            resetDeleteComponents();
            resetLoadComponents();
            resetSearchComponents();

            if(!titleTF.getText().isEmpty())
            {
                try
                {
                    saver.save(rapTP, titleTF);
                    System.out.println("Saving: " + titleTF.getText());
                    fileUtils.getFiles(saveFilesCB, Strings.savesDir);
                    fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
                    setTempInfo("Saved!", "Save", 1500, saveBtn);
                }
                catch(IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            else
            {
                setTempInfo("No Title", "Save", 1500, saveBtn);
            }
        });

        //OpenBtn
        openBtn.addActionListener(e ->
        {
            resetDeleteComponents();
            resetSearchComponents();
            resetRenameComponents();

            if(fileUtils.hasFiles(Strings.savesDir))
            {
                if(!switchOnClick(savesShowing, openBtn, saveFilesCB, "Open File", "Select"))
                {
                    fileUtils.loadFile(rapTP, titleTF, Strings.savesDir, saveFilesCB.getSelectedItem().toString());
                }
            }
            else
            {
                setTempInfo("No Saves", "Open File", 1500, openBtn);
            }
        });

        //DeleteBtn
        deleteBtn.addActionListener(e1 ->
        {
            resetLoadComponents();
            resetSearchComponents();
            resetRenameComponents();

            if(fileUtils.hasFiles(Strings.savesDir))
            {
                if(!switchOnClick(deleteSavesShowing, deleteBtn, deleteFilesCB, "Delete Files", "Delete"))
                {
                    fileUtils.deleteFile(rapTP, titleTF, Strings.savesDir, deleteFilesCB.getSelectedItem().toString());
                    fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
                    fileUtils.getFiles(saveFilesCB, Strings.savesDir);
                }
            }
            else
            {
                setTempInfo("No Files", "Delete Files", 1500, deleteBtn);
            }
        });

        //RenameBtn
        renameBtn.addActionListener(e ->
        {
            resetDeleteComponents();
            resetLoadComponents();
            resetSearchComponents();

            String fileName = renameFilesCB.getSelectedItem().toString();

            if(fileUtils.hasFiles(Strings.savesDir))
            {
                if(isFirstClick[0] && timesClicked[0] == 0)
                {
                    timesClicked[0]++;
                    renameFilesCB.setVisible(true);
                    renameBtn.setText("Select");
                    cancelBtn.setVisible(true);
                }
                else if(timesClicked[0] == 1)
                {
                    isFirstClick[0] = false;
                    timesClicked[0]++;
                    fileName = renameFilesCB.getSelectedItem().toString();
                    renameFilesCB.setVisible(false);
                    newNameTF.setVisible(true);
                    renameBtn.setText("Rename");
                    cancelBtn.setVisible(true);
                }
                else
                {
                    timesClicked[0] = 0;
                    isFirstClick[0] = true;
                    fileUtils.renameFile(fileName, newNameTF.getText());
                    fileUtils.getFiles(saveFilesCB, Strings.savesDir);
                    fileUtils.getFiles(deleteFilesCB, Strings.savesDir);
                    fileUtils.getFiles(renameFilesCB, Strings.savesDir);
                    newNameTF.setVisible(false);
                    renameBtn.setText("Rename Files");
                    cancelBtn.setVisible(false);
                    revalidate();
                    repaint();
                }
            }
            else
            {
                setTempInfo("No Files", "Rename Files", 1500, renameBtn);
            }
        });

        //SearchBtn
        searchBtn.addActionListener(e ->
        {
            resetLoadComponents();
            resetDeleteComponents();
            resetRenameComponents();

            if(!switchOnClick(searchShowing, searchBtn, searchTF, "Rhyming Words", "Search"))
            {
                rhymingWordsDLM.removeAllElements();

                APIUtils apiUtils = new APIUtils();
                apiUtils.hitAPI("http://www.stands4.com/services/v2/rhymes.php?uid=4396&tokenid=7ok5aFMY8y0i0HP1&term=", searchTF.getText(), "50", rhymingWordsDLM);
            }
        });

        //CancelBtn
        cancelBtn.setVisible(false);

        cancelBtn.addActionListener(e ->
        {
            resetLoadComponents();
            resetDeleteComponents();
            resetSearchComponents();
            resetRenameComponents();
            cancelBtn.setVisible(false);
        });

        //SettingsBtn
        settingsBtn.addActionListener(e -> showPanel("Settings"));

        //RhymingWordsList
        rhymingWordsList.setFixedCellHeight(20);
        rhymingWordsList.setFixedCellWidth(100);
        rhymingWordsList.setAlignmentX(Component.CENTER_ALIGNMENT);
        rhymingWordsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resetRhymingList();

        addComponents();
    }

    private void setTempInfo(String tempMessage, String finalMessage, int time, JButton button)
    {
        Timer tempTimer = new Timer(time, null);

        button.setText(tempMessage);

        tempTimer.addActionListener(e ->
        {
            button.setText(finalMessage);
            tempTimer.stop();
        });

        tempTimer.start();
    }

    private boolean switchOnClick(boolean[] showing, JButton button, JComponent component, String message, String message2)
    {
        if(showing[0])
        {
            showing[0] = false;
            button.setText(message);
            component.setVisible(showing[0]);
            cancelBtn.setVisible(false);
            return false;
        }
        else
        {
            showing[0] = true;
            button.setText(message2);
            component.setVisible(showing[0]);
            cancelBtn.setVisible(true);
            return true;
        }
    }

    private void resetRhymingList()
    {
        rhymingWordsDLM.removeAllElements();

        String[] emptyRhymeList = {"Click", "Rhyming Words", "To", "Search", "For", "Rhyming", "Words"};
        for(String word : emptyRhymeList)
        {
            rhymingWordsDLM.addElement(word);
        }
    }

    private void resetDeleteComponents()
    {
        deleteSavesShowing[0] = true;
        switchOnClick(deleteSavesShowing, deleteBtn, deleteFilesCB, "Delete Files", "");
    }

    private void resetLoadComponents()
    {
        savesShowing[0] = true;
        switchOnClick(savesShowing, openBtn, saveFilesCB, "Open File", "");
    }

    private void resetSearchComponents()
    {
        searchShowing[0] = true;
        switchOnClick(searchShowing, searchBtn, searchTF, "Rhyming Words", "");
    }

    public void resetRenameComponents()
    {
        renameFilesCB.setVisible(false);
        newNameTF.setVisible(false);
        renameBtn.setText("Rename Files");
        isFirstClick[0] = true;
        timesClicked[0] = 0;
    }

    private void addComponents()
    {
        //TopPanel
        topPanel.add(titleTF);

        //RightPanel
        rightPanel.add(rlScrollPane);
        rightPanel.add(Box.createRigidArea(new Dimension(50, 25)));
        rightPanel.add(clearBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(50, 5)));
        rightPanel.add(insertBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(50, 5)));

        //Bottom Panel
        bottomPanel.add(createBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(saveBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(saveFilesCB);
        bottomPanel.add(openBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(deleteFilesCB);
        bottomPanel.add(deleteBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(renameFilesCB);
        bottomPanel.add(newNameTF);
        bottomPanel.add(renameBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(searchTF);
        bottomPanel.add(searchBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(settingsBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 15)));
        bottomPanel.add(cancelBtn);

        //Main Panel
        add(topPanel, BorderLayout.NORTH);
        add(rapTP, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}