package elastic;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zsjanos on 2016.11.08..
 */
public class FlatTokenUploader {

    private static final int WORDFORM_INDEX = 1;
    private static final int LEMMA_INDEX = 2;
    private static final int POS_INDEX = 4;
    private static final int PARENT_INDEX = 8;
    private static final int DEP_INDEX = 10;

    private static final String[] ROOT = new String[]{"0", "ROOT", "ROOT", "", "ROOT", "", "", "", "", "", ""};


    private Settings settings = Settings.settingsBuilder()
            .put("cluster.name", "elasticsearch").build();

    private TransportClient client = null;

    /**
     *
     */
    public FlatTokenUploader() {
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void upload(String file) {
        List<String> lines = Reader.readLines(file);
        System.out.println(lines.size());

        List<String[]> sentence = new LinkedList<>();

        for (String line : lines) {
            if (line.trim().length() == 0) {
                for (String[] token : sentence) {
                    String[] parent = getParentById(sentence, Integer.parseInt(token[PARENT_INDEX]));

                    FlatElement flatElement = new FlatElement(token[WORDFORM_INDEX],
                            token[LEMMA_INDEX], token[POS_INDEX], token[DEP_INDEX],
                            parent[WORDFORM_INDEX], parent[LEMMA_INDEX], parent[POS_INDEX]);

                    System.out.println(flatElement.toJson());

                    IndexResponse response = client.prepareIndex("users", "user")
                            .setSource(flatElement.toJson())
                            .execute()
                            .actionGet();

                    System.out.println(response.getId());
                }
                sentence = new LinkedList<>();
            } else {
                sentence.add(line.split("\t"));
            }
        }
    }

    /**
     * @param sentence
     * @return
     */
    private static String[] getParentById(List<String[]> sentence, int parentId) {
        if (parentId == 0) {
            return ROOT;
        }

        return sentence.get(parentId - 1);
    }


    public static void main(String[] args) {
        new FlatTokenUploader().upload("./data/newsml.infra.dep.2");
    }
}