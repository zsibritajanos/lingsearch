package elastic;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by zsjanos on 2016.10.28..
 */
public class HttpUplader {


    public static String testElasticSearch() {

        try {

            //  url = new URL("http://localhost:9200/users/user/_search?q=lemma:" + "MSZP" + "&pretty=true");

            String param = URLEncoder.encode("-d'{\"name\" : \"xxx\"}'", "utf-8");
            URL url = new URL("http://localhost:9200/users/user/_search?q=lemma:" + "mszp" + "&pretty=true");

            System.out.println(url);

            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpUrlConnection.connect();

            InputStream is = new BufferedInputStream(httpUrlConnection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            String result = stringBuilder.toString();
            is.close();
            responseStreamReader.close();
            httpUrlConnection.disconnect();
            return result;

        } catch (IOException e) {

            StringWriter sw = new StringWriter();

            PrintWriter pw = new PrintWriter(sw);

            e.printStackTrace(pw);

            return sw.toString();

        }

    }

    public static String executePost(String request) {

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

    public static void main(String[] args) {
        //System.out.println(executePost("http://localhost:9200/users"));

        System.out.println(testElasticSearch());

    }
}
