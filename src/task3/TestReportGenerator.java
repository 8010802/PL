package task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestReportGenerator {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать имена двух файлов в терминале.\n" +
                    "java -classpath \"src/task3/gson-2" +
                    ".10.1.jar;src\" task3.TestReportGenerator \"src/task3/tests.json\" \"src/task3/values.json\"");
            return;
        }

// java -classpath "src/task3/gson-2.10.1.jar;src" task3.TestReportGenerator "src/task3/tests.json" "src/task3/values.json"


        String testsFile = args[0];
        String valuesFile = args[1];

        try {
            // Чтение файла tests.json
            JsonElement testsJsonElement = readJsonFile(testsFile);
            JsonObject testsJsonObject = testsJsonElement.getAsJsonObject();

            // Чтение файла values.json
            JsonElement valuesJsonElement = readJsonFile(valuesFile);
            JsonObject valuesJsonObject = valuesJsonElement.getAsJsonObject();

            // Заполнение значениями из values.json
            fillValues(testsJsonObject, valuesJsonObject);

            // Запись результата в report.json
            writeJsonFile("report.json", testsJsonObject);
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи файла.");
            e.printStackTrace();
        }
    }

    private static JsonElement readJsonFile(String fileName) throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(fileName);
        return gson.fromJson(fileReader, JsonElement.class);
    }

    private static void writeJsonFile(String fileName, JsonElement jsonElement) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(gson.toJson(jsonElement));
        fileWriter.close();
    }

    private static void fillValues(JsonObject testsJsonObject, JsonObject valuesJsonObject) {
        if (testsJsonObject.has("tests")) {
            JsonArray testsArray = testsJsonObject.getAsJsonArray("tests");
            for (JsonElement testElement : testsArray) {
                JsonObject testObject = testElement.getAsJsonObject();
                fillValue(testObject, valuesJsonObject);
                if (testObject.has("values")) {
                    fillValues(testObject, valuesJsonObject);
                }
            }
        }
    }

    private static void fillValue(JsonObject testObject, JsonObject valuesJsonObject) {
        if (testObject.has("id") && testObject.has("value")) {
            int testId = testObject.get("id").getAsInt();
            if (valuesJsonObject.has("values")) {
                JsonArray valuesArray = valuesJsonObject.getAsJsonArray("values");
                for (JsonElement valueElement : valuesArray) {
                    JsonObject valueObject = valueElement.getAsJsonObject();
                    if (valueObject.has("id") && valueObject.has("value")) {
                        int valueId = valueObject.get("id").getAsInt();
                        if (testId == valueId) {
                            testObject.addProperty("value", valueObject.get("value").getAsString());
                            break;
                        }
                    }
                }
            }
        }
    }
}
