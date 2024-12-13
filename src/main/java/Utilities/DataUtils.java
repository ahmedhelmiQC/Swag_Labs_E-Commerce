package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Properties;

public class DataUtils {
    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";

    // Retrieve a specific field value from a JSON file
    public static String getJosnData(String filename , String field) throws FileNotFoundException {
        try {
            FileReader reader = new FileReader(TEST_DATA_PATH +filename+".json");
            //// convert Json File To Json Element ///
            JsonElement jsonElement = JsonParser.parseReader(reader);
            /// return Json Element AsString //
            return jsonElement.getAsJsonObject().get(field).getAsString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    // Retrieve a key's value from a properties file
    public static String getPropertyData(String filename , String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH +filename+".properties"));
        ////The getProperty(key)  retrieves the key value from the loaded properties file and returns it as a String
           return properties.getProperty(key);
    }
}
