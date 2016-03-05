package logic.utilities;

/*
 * Created by Jonah on 2/24/2016.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ParsingUtils
{
    public ParsingUtils()
    {
        System.out.println("Parsing Utils Loaded");
    }

    public String[] parseJSON(String input)
    {
        String[] results = new String[200];

        JSONArray JSONrtrn = new JSONArray(input);

        for (int i = 0; i < JSONrtrn.length(); ++i)
        {
            JSONObject parsedObj = (JSONObject) JSONrtrn.get(i);
            String SLrtrn = parsedObj.get("word").toString();

            String StringScore = parsedObj.get("score").toString();

            int Score = Integer.parseInt(StringScore);

            if(Score >= 200)
            {
                results[i] = SLrtrn;
            }
        }

        return results;
    }

    public String[] parseXML(String file) throws FileNotFoundException, XMLStreamException
    {
        String[] rhymes = new String[250];
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream(file);
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
        streamReader.nextTag();
        streamReader.nextTag();

        while(streamReader.hasNext())
        {
            if(streamReader.isStartElement())
            {
                switch (streamReader.getLocalName())
                {
                    case "rhymes":
                    {
                        rhymes = streamReader.getElementText().split(",");
                        break;
                    }
                }
            }
            streamReader.next();
        }

        return rhymes;
    }
}