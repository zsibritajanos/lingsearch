package elastic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zsjanos on 2016.10.27..
 */
public class Reader {

    public static List<Token> read(String file) {
        List<Token> tokens = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 0) {
                    tokens.add(new Token(line.split("\t")[1], line.split("\t")[2], line.split("\t")[3], line.split("\t")[7]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokens;
    }

    public static void main(String[] args) {
        System.out.print(read("./data/hvg.conll").size());
    }
}
