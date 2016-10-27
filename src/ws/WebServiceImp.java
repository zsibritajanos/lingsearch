package ws;


import elastic.Elastic;

import javax.jws.WebService;

/**
 * Created by zsibritajanos on 2016.01.20..
 */
@WebService(endpointInterface = "ws.WebServiceInterface")
public class WebServiceImp implements WebServiceInterface {

    @Override
    public String hello(String s) {
        Elastic elastic = new Elastic();
        return "Hello " + elastic.getElementById("AVgHwkff_Dj2_nYbAg8p") + "!";
    }

}
