package elastic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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


    /**
     * https://www.elastic.co/guide/en/elasticsearch/guide/current/_finding_multiple_exact_values.html
     * curl -XGET 'http://localhost:9200/users/user/_search?pretty' -d'{"query":{"constant_score":{"filter":{"bool":{"must":[{"term":{"dep":"cc"}},{"term":{"lemma":"is"}}]}}}}}'
     */

    public static String searchByMultiple(String childLemma, String childPos, String depRel, String parentLemma, String parentPos) {

        /**
         * curl -XDELETE 'localhost:9200/users?pretty'
         * curl -XPUT 'localhost:9200/users?pretty' -d'{"mappings":{"user":{"properties":{"depRel":{"type":"string","index":"not_analyzed"}, "childPos":{"type":"string","index":"not_analyzed"}, "parentPos":{"type":"string","index":"not_analyzed"}}}}}'
         * http://localhost:9200/users/_mappings
         */

        StringBuffer postData = new StringBuffer("{\"from\" : 0, \"size\" : 10000, \"query\": {\"constant_score\" : {\"filter\" : {\"bool\" : {\"must\" : [{ \"term\" : { \"depRel\" : \"" + depRel + "\" } }");

        if (childLemma != null && childLemma.trim().length() > 0) {
            postData.append(", { \"term\" : { \"childLemma\" : \"" + childLemma.toLowerCase() + "\" } }");
        }
        if (childPos != null && childPos.trim().length() > 0) {
            postData.append(", { \"term\" : { \"childPos\" : \"" + childPos + "\" } }");
        }
        if (parentLemma != null && parentLemma.trim().length() > 0) {
            postData.append(", { \"term\" : { \"parentLemma\" : \"" + parentLemma.toLowerCase() + "\" } }");
        }
        if (parentPos != null && parentPos.trim().length() > 0) {
            postData.append(", { \"term\" : { \"parentPos\" : \"" + parentPos + "\" } }");
        }

        postData.append(" ]}}}}}");
        System.out.println(postData);

        URL url;
        StringBuilder resultBuf = new StringBuilder();
        try {
            url = new URL("http://localhost:9200/users/user/_search");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(true);

            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));

            writer.write(postData.toString());
            writer.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            for (String line; (line = reader.readLine()) != null; ) {
                resultBuf.append(line + "\n");
                System.out.println(line);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return toString(resultBuf.toString());
    }


    public static void main(String[] args) {
        System.out.println(searchByMultiple("otp", null, "NE", null, null));
    }
}
