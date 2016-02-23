package logic.utilities;

/*
 * Created by Jonah on 2/11/2016.
 */

import local.Strings;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver
{
    public Saver()
    {
        System.out.println("Saver Loaded");
    }

    public void save(JTextPane textPane, JTextField titleField) throws IOException
    {
        FileWriter fileWriter = new FileWriter(new File(Strings.savesDir, titleField.getText()));

        SwingWorker<Void, Void> saver = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                fileWriter.write(titleField.getText() + "\n");
                fileWriter.write(textPane.getText());

                return null;
            }

            /*@Override
            protected void process(List<Void> chunks)
            {

            }*/

            @Override
            protected void done()
            {
                try
                {
                    fileWriter.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        };

        saver.execute();
    }
}