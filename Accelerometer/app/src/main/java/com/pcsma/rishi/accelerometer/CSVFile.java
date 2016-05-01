package com.pcsma.rishi.accelerometer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Rishi on 12/01/16.
 */
public class CSVFile {
    InputStream inputStream;
    public CSVFile(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
    public String read()
    {
        //List resultList = new ArrayList();
        String acc = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String csvLine;
            while ((csvLine = bufferedReader.readLine()) != null)
            {
                acc += csvLine;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return acc;
    }
}
