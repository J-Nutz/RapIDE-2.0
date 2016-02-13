package logic.utilities;

/*
 * Created by Jonah on 2/12/2016.
 */

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class FileUtils
{
    String fileName;

    public FileUtils()
    {

    }

    public void loadFile(JTextComponent component, String path, String file)
    {
        Reader mReader;

        fileName = file;

        try
        {
            mReader = new FileReader(new File(path + file + ".txt"));
            component.read(mReader, "What? Lol.");
            mReader.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
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
                    cb.addItem(file.getName().replace(".txt", ""));
                }
                else if(file.isDirectory())
                {
                    System.out.println("No Files");
                }
            }
        }
    }

    public void deleteFile(JTextComponent component, String path, String file)
    {
        try
        {
            File fileToDelete = new File(path + file + ".txt");

            if(fileToDelete.delete())
            {
                component.setText("");
            }
        }
        catch(Exception de)
        {
            System.out.println("DeleterPane Error: " + de);
        }
    }
}
