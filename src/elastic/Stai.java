//package elastic;
//
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by zsjanos on 2016.10.27..
// */
//public class Stai {
//
//    /**
//     * @param name
//     * @param age
//     * @return
//     */
//    public static Map<String, Object> putJsonDocument(String name, int age) {
//        Map<String, Object> jsonDocument = new HashMap<>();
//        jsonDocument.put("name", name);
//        jsonDocument.put("age", age);
//        return jsonDocument;
//
//    }
//
//    public static void lsitItems() {
//        Settings settings = Settings.settingsBuilder()
//                .put("cluster.name", "elasticsearch").build();
//
//        TransportClient client = null;
//        try {
//            client = TransportClient.builder().settings(settings).build()
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        client.prepareIndex("users", "user", "2")
//                .setSource(putJsonDocument("maef", 100)).execute().actionGet();
//
//
//        GetResponse getResponse = client.prepareGet("users", "user", "2").execute().actionGet();
//
//        Map<String, Object> source = getResponse.getSource();
//        System.out.println("------------------------------");
//        System.out.println("Index: " + getResponse.getIndex());
//        System.out.println("Type: " + getResponse.getType());
//        System.out.println("Id: " + getResponse.getId());
//        System.out.println("Version: " + getResponse.getVersion());
//        System.out.println(source);
//        System.out.println("------------------------------");
////        searchJava();
//
//    }
//
//    public static void main(String[] args){
//        Settings settings = Settings.settingsBuilder()
//                .put("cluster.name", "elasticsearch").build();
//
//        TransportClient client = null;
//        try {
//            client = TransportClient.builder().settings(settings).build()
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9200));
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
////        client.prepareIndex("users", "user", "2")
////                .setSource(putJsonDocument("maef", 100)).execute().actionGet();
////
////
////        GetResponse getResponse = client.prepareGet("users", "user", "2").execute().actionGet();
////
////        Map<String, Object> source = getResponse.getSource();
////        System.out.println("------------------------------");
////        System.out.println("Index: " + getResponse.getIndex());
////        System.out.println("Type: " + getResponse.getType());
////        System.out.println("Id: " + getResponse.getId());
////        System.out.println("Version: " + getResponse.getVersion());
////        System.out.println(source);
////        System.out.println("------------------------------");
////        searchJava();
//    }
//}
