import org.json.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        String input = "{\"status\":0,\"body\":{\"activities\":[{\"date\":\"2014-12-11\",\"steps\":66,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-12\",\"steps\":136,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-13\",\"steps\":2012,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-14\",\"steps\":53,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-15\",\"steps\":0,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"}]}}";

        String output = DataPaser(input, "date", "steps");//getDataFromJson(input, "data", "steps", "calories", "elevation");

        System.out.println(input);
        System.out.println();
        System.out.println(output);
    }

    public static String getDataFromJson(String JsonInput, String Key, String KeyEnd, String Value, String ValueEnd) {
        String cleanInput = JsonInput.substring(JsonInput.indexOf("[{"), JsonInput.indexOf("}]") + 2);
        String[] splitInput = cleanInput.split("\\},\\{");
        String result = "[";
        for (int i = 0; i < splitInput.length; i++) {
            String X = splitInput[i].substring(splitInput[i].indexOf(Key + "\":\"") + Key.length(), splitInput[i].indexOf("\",\"" + KeyEnd));
            String Y = splitInput[i].substring(splitInput[i].indexOf(Value + "\":") + Value.length(), splitInput[i].indexOf(",\"" + ValueEnd));
            result += ("{\"" + Key + "\":\"" + X + "\",\"" + Value + "\":" + Y + "}");
            if (i < (splitInput.length - 1)) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }


    private static String DataPaser(String DataInput, String Key, String Value) {
        String result = "[";
        int max, min;
        try {
            JSONObject job = new JSONObject(DataInput);
            JSONObject body = (job.getJSONObject("body"));
            JSONArray act = body.getJSONArray("activities");

            //get the max and min value
            max = act.getJSONObject(0).getInt("steps");
            min = act.getJSONObject(0).getInt("steps");

            for (int i = 0; i < act.length(); i++) {
                String valueA = act.getJSONObject(i).getString("date");
                int valueB = act.getJSONObject(i).getInt("steps");

                //update max and min
                if (valueB > max) {
                    max = valueB;
                }
                if (valueB < min) {
                    min = valueB;
                }

                result += ("{\"" + Key + "\":\"" + valueA + "\",\"" + Value + "\":" + valueB + "}");
                if (i < (act.length() - 1)) {
                    result += ",";
                }
            }
            result += "]";

            //set max and min a better range
            int diff = (max - min) / 2;
            max += diff;
            min -= diff;
            if(min < 0){
                min = 0;
            }

            //output to Graph.js file
            BufferedWriter buff = new BufferedWriter(new FileWriter(new File("Graph.js")));
            buff.write("var max = " + max + ";\r\nvar min = " + min + ";\r\nvar data=" + result + ";\r\n");
            buff.flush();
            buff.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }


}
