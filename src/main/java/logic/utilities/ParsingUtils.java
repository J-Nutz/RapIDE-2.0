package logic.utilities;

/*
 * Created by Jonah on 2/24/2016.
 */

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

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

    public String[] parseXML(String tag, File xml)
    {
        String[] rhymes = null;

        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(tag);

            for(int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    String tempRhymes = eElement.getElementsByTagName("rhymes").item(0).getTextContent();

                    rhymes = tempRhymes.split(",");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(rhymes == null || rhymes.length == 0)
            {
                rhymes = new String[]{"No", "Rhymes"};
            }
        }
        return rhymes;
    }
}