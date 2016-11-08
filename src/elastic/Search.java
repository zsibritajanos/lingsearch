package elastic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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

    /**
     * @return
     */

    // {childPos=N, parentWordForm=részvényeinek, depRel=ATT, childWordForm=Sony, parentPos=N, parentLemma=részvény, childLemma=Sony}
    public static String searchByLemma(String childLemma) {
        //return ask("http://localhost:9200/users/user/_search?q=lemma:" + lemma + "&pretty=true");
        return ask("http://localhost:9200/users/user/_search?q=childLemma:" + childLemma + "&pretty=true");
    }

    public static String searchByPosDepPos(String depRel) {
        return ask("http://localhost:9200/users/user/_search?q=depRel:" + depRel + "&pretty=true");
    }

    public static void main(String[] args) {
        System.out.println(searchByPosDepPos("NE"));
    }
}
