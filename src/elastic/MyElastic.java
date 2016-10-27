package elastic;

import org.elasticsearch.action.fieldstats.FieldStats;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by zsjanos on 2016.10.18..
 */
public class MyElastic {

    private static final String URL = "http://localhost:9200/";


    // $ curl -XPUT 'http://localhost:9200/twitter/tweet/1' -d '{"user" : "kimchy", "post_date" : "2009-11-15T14:12:12", "message" : "trying out Elasticsearch"}'
    // http://joelabrahamsson.com/elasticsearch-101/

    /**
     *
     */
    private static void test() {
        URL url = null;
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private static void run(String request) {
        URL url;
        BufferedReader reader;

        try {
            url = new URL(request);
            reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            for (String line; (line = reader.readLine()) != null; ) {
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private static void javaTest() {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();


        TransportClient client = null;
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


        } catch (IOException e) {
            e.printStackTrace();
        }

        SearchResponse response = client.prepareSearch().execute().actionGet();
        System.out.printf(String.valueOf(response.getHits().hits()[0].getId()));
    }

    private static void searchJava(){

        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = null;
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


        } catch (IOException e) {
            e.printStackTrace();
        }


        SearchResponse response = client.prepareSearch("users")
                .setTypes("user")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(40))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();

        System.out.println(response);
        SearchResponse response2 = client.prepareSearch().execute().actionGet();
        System.out.println(response2);
    }


    private static void addJavaItem(){
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = null;
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = "{" +
                "\"user\":\"crvn\"," +
                "\"age\":\"20\"" +
                "}";

        System.out.println(json);

        IndexResponse response = client.prepareIndex("users", "user")
                .setSource(json)
                .execute()
                .actionGet();

        System.out.println(response);

    }
    private static void addItem(String name, int age) {


        // curl -XPUT "http://localhost:9200/users/user/1" -d'{"name:": "janos", "age": "32"}'
        // curl -XGET "http://localhost:9200/users/user/1" -d'{}'
        // curl -XDELETE "http://localhost:9200/users/user/1" -d'{}'

        String path = "-XPUT \"" + URL + "users/user/1" + "\" -d'{\"name:\": \"janos\", \"age\": \"32\"}'";
        System.out.printf(path);
        //run(path);
    }


    /**
     *
     * @param name
     * @param age
     * @return
     */
    public static Map<String, Object> putJsonDocument(String name, int age){
        Map<String, Object> jsonDocument = new HashMap<>();
        jsonDocument.put("name", name);
        jsonDocument.put("age", age);
        return jsonDocument;

    }

    /**
     *
     * @param args
     *
     * https://dzone.com/articles/elasticsearch-java-api
     */
    public static void main(String[] args) {
        //test();
        //addItem("a", 10);




        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = null;
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


        } catch (IOException e) {
            e.printStackTrace();
        }


        client.prepareIndex("users", "user", "2")
                .setSource(putJsonDocument("maef", 100)).execute().actionGet();



        GetResponse getResponse = client.prepareGet("users", "user", "2").execute().actionGet();

        Map<String, Object> source = getResponse.getSource();
        System.out.println("------------------------------");

        System.out.println("Index: " + getResponse.getIndex());

        System.out.println("Type: " + getResponse.getType());

        System.out.println("Id: " + getResponse.getId());

        System.out.println("Version: " + getResponse.getVersion());

        System.out.println(source);

        System.out.println("------------------------------");

//        searchJava();

    }
}


