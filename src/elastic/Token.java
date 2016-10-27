package elastic;

import ws.WebServiceImp;

/**
 * Created by zsjanos on 2016.10.27..
 */
public class Token {

    // 1	Az	az	Tf	_	Tf	4	det	_	_
    public String word;
    public String lemma;
    public String morph;
    public String dep;

    public Token(String word, String lemma, String morph, String dep) {
        this.word = word;
        this.lemma = lemma;
        this.morph = morph;
        this.dep = dep;
    }

    public Token(){

    }

    public static void main(String[] args){
        System.out.println(WebServiceImp.test("http://localhost:9200/users/_search -d'{}'"));
    }
}
