package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Properties;

public class DataUtli {
    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";

    public static String getJosnData(String filename , String field) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH +filename+".json");
                //// convert Json File To Json Element ///
        JsonElement jsonElement = JsonParser.parseReader(reader);
                /// return Json Element AsString //
        return jsonElement.getAsJsonObject().get(field).getAsString();
    }

    public static String getPropertyData(String filename , String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH +filename+".properties"));
        ////The getProperty(key)  retrieves the key value from the loaded properties file and returns it as a String
           return properties.getProperty(key);
    }
}
