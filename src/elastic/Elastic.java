package elastic;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsjanos on 2016.10.27..
 */
public class Elastic {

    private Settings settings = Settings.settingsBuilder()
            .put("cluster.name", "elasticsearch").build();

    private TransportClient client = null;


    public Elastic() {
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getJson(String name, int age) {
        Map<String, Object> jsonDocument = new HashMap<>();
        jsonDocument.put("name", name);
        jsonDocument.put("age", age);
        return jsonDocument;
    }

    public void upload() {


        Map user = getJson("ccc", 44);
        System.out.println(user);

        IndexResponse response = client.prepareIndex("users", "user")
                .setSource()
                .execute()
                .actionGet();

        System.out.println(response.getId());
    }


    public static void main(String[] args) {
        Elastic elastic = new Elastic();
        elastic.upload();
    }
}
