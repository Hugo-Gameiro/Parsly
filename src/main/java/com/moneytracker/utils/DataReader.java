package com.moneytracker.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class DataReader {

    @DataProvider
    public Object[][] userData() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            Object obj = parser.parse(new FileReader(
                    System.getProperty("user.dir") + "/src/test/resources/testData.json"));
            jsonObject = (JSONObject) obj;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[1][1];

        HashMap<String, String> hashMap = new LinkedHashMap<>();
        if (jsonObject != null) {
            Set<String> jsonObjKeys = jsonObject.keySet();
            for (String jsonObjKey : jsonObjKeys) {
                hashMap.put(jsonObjKey, (String) jsonObject.get(jsonObjKey));
            }
        } else {
            throw new RuntimeException("Error retrieving JSON data");
        }

        data[0][0] = hashMap;
        return data;
    }
}
