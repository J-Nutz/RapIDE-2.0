package logic.utilities;

/*
 * Created by Jonah on 2/23/2016.
 */

import global.Strings;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class APIUtils
{

    public APIUtils()
    {

    }

    public void hitAPI(String api, String word, String queries, DefaultListModel<String> dlm)
    {
        final URLConnection[] APIconnection = new URLConnection[1];
        final BufferedReader[] APIreader = new BufferedReader[1];

        SwingWorker<Void, String> apiWorker = new SwingWorker<Void, String>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                try
                {
                    URL API = new URL(api + word);

                    APIconnection[0] = API.openConnection();

                    APIreader[0] = new BufferedReader(new InputStreamReader(APIconnection[0].getInputStream()));

                    String input;

                    while((input = APIreader[0].readLine()) != null)
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
                    FileWriter mFileWriter = new FileWriter(new File(Strings.xmlDir, "xmlSave.xml"));

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

                File xmlSave = new File(Strings.xmlDir + "xmlSave.xml");

                ParsingUtils parsingUtils = new ParsingUtils();
                String[] rhymingWords = parsingUtils.parseXML("result", xmlSave);

                for(String rhymeWord : rhymingWords)
                {
                    dlm.addElement(rhymeWord);
                }

            }
        };
        apiWorker.execute();
    }
}