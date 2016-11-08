package elastic;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by zsjanos on 2016.11.08..
 */
public class Test {
    private static TransportClient client = null;

    private static Settings settings = Settings.settingsBuilder()
            .put("cluster.name", "elasticsearch").build();

    public static void main(String[] args) {
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
