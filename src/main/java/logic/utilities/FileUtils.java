package logic.utilities;

/*
 * Created by Jonah on 2/12/2016.
 */

import global.Strings;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils
{
    public FileUtils()
    {
        System.out.println("File Utils Loaded");
    }

    public void loadFile(JTextPane pane, JTextField field, String path, String file)
    {
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
            System.out.println("Error Loading File: " + e.getMessage());
        }
    }

    public void getFiles(JComboBox<String> cb, String path)
    {
        cb.removeAllItems();

        File savesFolder = new File(path);
        File[] listOfSaves = savesFolder.listFiles();

        if(listOfSaves != null)
        {
            for(File file : listOfSaves)
            {
                if(file.isFile())
                {
                    cb.addItem(file.getName());
                }
                else if(file.isDirectory())
                {
                    System.out.println("Tried To Add Folder");
                }
            }
        }
    }

    public boolean hasFiles(String path)
    {
        File savesFolder = new File(path);
        File[] listOfSaves = savesFolder.listFiles();

        return listOfSaves != null && listOfSaves.length > 0;
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
                    System.out.println("Deleting: " + file);
                }
            }
        }
        catch(Exception de)
        {
            System.out.println("Error Deleting File: " + de.getMessage());
        }
    }

    public void createBaseFolders()
    {
        File savesFTP = new File(Strings.savesDir);
        Path savesPath = savesFTP.toPath();

        File xmlFTP = new File(Strings.xmlDir);
        Path xmlPath = xmlFTP.toPath();

        //File propFTP = new File(Strings.pathToProps);
        //Path propPath = propFTP.toPath();

        try
        {
            Files.createDirectories(savesPath);
            Files.createDirectories(xmlPath);
            //Files.createDirectories(propPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}