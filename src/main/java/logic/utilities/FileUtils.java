package logic.utilities;

/*
 * Created by Jonah on 2/12/2016.
 */

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils
{
    String fileName;

    public FileUtils()
    {

    }

    public void loadFile(JTextPane pane, JTextField field, String path, String file)
    {
        fileName = file;

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + file));

            field.setText(bufferedReader.readLine());
            pane.setText("");

            pane.read(bufferedReader, "Default");
            bufferedReader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getFiles(JComboBox<String> cb, String path)
    {
        cb.removeAllItems();

        File savesFolder = new File(path);
        File[] listOfFiles = savesFolder.listFiles();

        if(listOfFiles != null)
        {
            for(File file : listOfFiles)
            {
                if(file.isFile())
                {
                    cb.addItem(file.getName());
                }
                else if(file.isDirectory())
                {
                    System.out.println("No Files");
                }
            }
        }
    }

    public void deleteFile(JTextComponent component, JTextField textField, String path, String file)
    {
        try
        {
            File fileToDelete = new File(path + file);

            if(fileToDelete.delete())
            {
                if(textField.getText().equals(file))
                {
                    component.setText("");
                }
            }
        }
        catch(Exception de)
        {
            System.out.println("DeleterPane Error: " + de);
        }
    }
}