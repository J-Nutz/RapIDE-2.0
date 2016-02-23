package logic.utilities;

/*
 * Created by Jonah on 2/23/2016.
 */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class APIUtils
{

    public APIUtils()
    {

    }

    public void hitAPI(URL api, String word, String queries)
    {
        final URLConnection[] APIconnection = new URLConnection[1];
        final BufferedReader[] APIreader = new BufferedReader[1];

        SwingWorker<Void, Void> apiWorker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                try
                {
                    URL API = new URL(api + word + queries);

                    APIconnection[0] = API.openConnection();

                    APIreader[0] = new BufferedReader(new InputStreamReader(APIconnection[0].getInputStream()));
                }
                catch(MalformedURLException e)
                {
                    e.printStackTrace();
                }


                return null;
            }
        };

    }

}