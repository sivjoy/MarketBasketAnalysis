package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by siv on 2/27/15.
 */
public class FileUtils {

    BufferedReader br = null;
    String line ="";
    String csvSplitBy=",";

    public List<String> getAllProductValues(String fileLoc){
        List<String> values = new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader(fileLoc));
            while((line=br.readLine())!=null){
                String[] product = line.split(csvSplitBy);
                values.add(product[1]);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("The product values are : "+values);
        return values;
    }

    public Map<String,String> getAllProductIDsAndValues(String fileLoc){
        Map<String,String> values = new HashMap<String, String>();

        try {
            br = new BufferedReader(new FileReader(fileLoc));
            while((line=br.readLine())!=null){
                String[] product = line.split(csvSplitBy);
                values.put(product[0], product[1]);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("The product values are : "+values);
        return values;
    }

}
