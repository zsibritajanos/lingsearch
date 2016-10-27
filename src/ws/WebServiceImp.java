package ws;


import elastic.Elastic;

import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zsibritajanos on 2016.01.20..
 */
@WebService(endpointInterface = "ws.WebServiceInterface")
public class WebServiceImp implements WebServiceInterface {

    public static String test(String request) {
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

    @Override
    public String hello(String s) {

        http://localhost:9200/users/user/_search?pretty%27%20-d%20%27{%22query%22:%20{%20%22match%22:%20{%22morph%22%20:%20%22Afp-sn%22%20}%20}}


        return "Heeello " + s + "!" + test("http://localhost:9200/");
    }
}
