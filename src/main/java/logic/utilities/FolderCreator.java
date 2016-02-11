package logic.utilities;

/*
 * Created by Jonah on 2/11/2016.
 */

import local.Strings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FolderCreator
{
    public FolderCreator()
    {
        File savesFTP = new File(Strings.savesDir);
        Path savesPath = savesFTP.toPath();

        //File xmlFTP = new File(Strings.pathToXML);
        //Path xmlPath = xmlFTP.toPath();

        //File propFTP = new File(Strings.pathToProps);
        //Path propPath = propFTP.toPath();

        try
        {
            Files.createDirectories(savesPath);
            //Files.createDirectories(xmlPath);
            //Files.createDirectories(propPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
