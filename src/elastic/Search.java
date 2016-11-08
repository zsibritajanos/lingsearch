package elastic;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zsjanos on 2016.10.28..
 */
public class Search {

    private static String ask(String request) {
        URL url;
        BufferedReader reader;

        String ret = "";

        try {
            url = new URL(request);
            reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            for (String line; (line = reader.readLine()) != null; ) {
                ret += line + "\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    private static String toString(String response) {

        StringBuffer textResponse = new StringBuffer();

        // JSON objects
        JSONArray hitsArray = null;
        JSONObject hits = null;
        JSONObject source = null;
        JSONObject json = null;
        try {
            json = new JSONObject(response);
            hits = json.getJSONObject("hits");
            hitsArray = hits.getJSONArray("hits");

            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject h = hitsArray.getJSONObject(i);
                source = h.getJSONObject("_source");
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(source.getString("childWordForm"));
                stringBuffer.append("/");
                stringBuffer.append(source.getString("childLemma"));
                stringBuffer.append("@");
                stringBuffer.append(source.getString("childPos"));

                stringBuffer.append(" ");
                stringBuffer.append(source.getString("depRel"));
                stringBuffer.append(" ");

                stringBuffer.append(source.getString("parentWordForm"));
                stringBuffer.append("/");
                stringBuffer.append(source.getString("parentLemma"));
                stringBuffer.append("@");
                stringBuffer.append(source.getString("parentPos"));

                textResponse.append(stringBuffer.toString() + '\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return textResponse.toString();
    }

    /**
     * @return
     */

    // {childPos=N, parentWordForm=részvényeinek, depRel=ATT, childWordForm=Sony, parentPos=N, parentLemma=részvény, childLemma=Sony}
    public static String searchByLemma(String childLemma) {
        //return ask("http://localhost:9200/users/user/_search?q=lemma:" + lemma + "&pretty=true");
        return ask("http://localhost:9200/users/user/_search?q=childLemma:" + childLemma + "&pretty=true&limit=50");
    }

    public static String searchByDep(String depRel) {
        return toString(ask("http://localhost:9200/users/user/_search?q=depRel:" + depRel + "&pretty=true&size=100")).toString();
    }

    public static void main(String[] args) {


        System.out.println(searchByDep("NE"));


        //System.out.println(searchByDep("NE"));
    }
}
