package elastic;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsjanos on 2016.10.27..
 */
public class Elastic {

    private TransportClient client = null;

    private Settings settings = Settings.settingsBuilder()
            .put("cluster.name", "elasticsearch").build();

    public Elastic() {

        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getLocalHost(), 9300));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param word
     * @param lemma
     * @param morph
     * @param dep
     * @return
     */
    public static Map<String, Object> getJson(String word, String lemma, String morph, String dep) {
        Map<String, Object> jsonDocument = new HashMap<>();
        jsonDocument.put("word", word);
        jsonDocument.put("lemma", lemma);
        jsonDocument.put("morph", morph);
        jsonDocument.put("dep", dep);
        return jsonDocument;
    }

    public void load() {
        List<Token> tokens = Reader.read("./data/hvg.conll");

        for (Token token : tokens) {
            Map j = getJson(token.word, token.lemma, token.morph, token.dep);
            System.out.println(j);

            IndexResponse response = client.prepareIndex("users", "user")
                    .setSource(j)
                    .execute()
                    .actionGet();

            System.out.println(response.getId());
        }
    }

    public Map<String, Object> getElementById(String id) {
        GetResponse getResponse = client.prepareGet("users", "user", id).execute().actionGet();
        return getResponse.getSource();
    }


    public static void main(String[] args) {
        Elastic elastic = new Elastic();
        //elastic.load();
        System.out.println(elastic.getElementById("AVgHwkff_Dj2_nYbAg8p"));
    }
}
