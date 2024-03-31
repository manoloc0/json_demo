package org.example;

import org.example.JSONUtility;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Assuming the JSON structure is similar to the XML one but in JSON format
            JSONObject doc = JSONUtility.loadJSONDocumentFromFile("/Users/tylercadenas/sde/json_demo2/src/main/java/org/example/dsm.json");
            JSONUtility.addDiagnosis(doc, "Example Disorder", "ED", "101", "999.9");
            JSONUtility.updateDiagnosisPage(doc, "Example Disorder", "102");
            //JSONUtility.removeDiagnosis(doc, "Example Disorder");
            JSONUtility.saveJSONDocumentToFile(doc, "/Users/tylercadenas/sde/json_demo2/src/main/java/org/example/dsm.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}