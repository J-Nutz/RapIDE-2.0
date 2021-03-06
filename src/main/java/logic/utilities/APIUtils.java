package logic.utilities;

/*
 * Created by Jonah on 2/23/2016.
 */

import global.Strings;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class APIUtils
{
    public APIUtils()
    {
        System.out.println("API Utils Loaded");
    }

    public void hitAPI(String api, String word, String queries, DefaultListModel<String> dlm)
    {
        SwingWorker<Void, String> apiWorker = new SwingWorker<Void, String>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                try
                {
                    URL API = new URL(api + word);
                    URLConnection connection = API.openConnection();

                    BufferedReader APIreader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String input;

                    while((input = APIreader.readLine()) != null)
                    {
                        publish(input);
                    }
                }
                catch(MalformedURLException e)
                {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks)
            {
                try
                {
                    FileWriter mFileWriter = new FileWriter(new File(Strings.xmlSave));

                    for(String input : chunks)
                    {
                        mFileWriter.write(input);
                    }

                    mFileWriter.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }

                ParsingUtils parsingUtils = new ParsingUtils();

                String[] rhymingWords = null;
                try
                {
                    rhymingWords = parsingUtils.parseXML(Strings.xmlSave);
                }
                catch(FileNotFoundException | XMLStreamException e)
                {
                    e.printStackTrace();
                }

                if(rhymingWords != null && !rhymingWords[0].equals(" "))
                {
                    for(String rhymeWord : rhymingWords)
                    {
                        dlm.addElement(rhymeWord.trim());
                    }
                }
                else
                {
                    dlm.addElement("No");
                    dlm.addElement("Rhymes");
                }
            }
        };
        apiWorker.execute();
    }
}