package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class JSONUtility {

    public static JSONObject loadJSONDocumentFromFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(content);
    }

    public static void saveJSONDocumentToFile(JSONObject jsonObject, String filePath) throws IOException {
        Files.write(Paths.get(filePath), jsonObject.toString(4).getBytes());
    }

    public static void addDiagnosis(JSONObject dsmObject, String name, String abbreviation, String page, String code) {
        JSONObject newDiagnosis = new JSONObject();
        newDiagnosis.put("Name", name);
        newDiagnosis.put("Abbreviation", abbreviation);
        newDiagnosis.put("Page", page);
        newDiagnosis.put("Code", code);
        // Get the "Diagnoses" array from the "DSM" object
        dsmObject.getJSONObject("DSM").getJSONArray("Diagnoses").put(newDiagnosis);
    }

    public static void updateDiagnosisPage(JSONObject dsmObject, String name, String newPage) {
        // Get the "Diagnoses" array from the "DSM" object
        JSONArray diagnosisArray = dsmObject.getJSONObject("DSM").getJSONArray("Diagnoses");
        for (int i = 0; i < diagnosisArray.length(); i++) {
            JSONObject diagnosis = diagnosisArray.getJSONObject(i);
            if (diagnosis.getString("Name").equals(name)) {
                diagnosis.put("Page", newPage);
                break;
            }
        }
    }

    public static void removeDiagnosis(JSONObject dsmObject, String name) {
        // Get the "Diagnoses" array from the "DSM" object
        JSONArray diagnosisArray = dsmObject.getJSONObject("DSM").getJSONArray("Diagnoses");
        for (int i = 0; i < diagnosisArray.length(); i++) {
            JSONObject diagnosis = diagnosisArray.getJSONObject(i);
            if (diagnosis.getString("Name").equals(name)) {
                diagnosisArray.remove(i);
                break;
            }
        }
    }
}
