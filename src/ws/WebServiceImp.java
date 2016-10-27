package ws;


import javax.jws.WebService;

/**
 * Created by zsibritajanos on 2016.01.20..
 */
@WebService(endpointInterface = "ws.WebServiceInterface")
public class WebServiceImp implements WebServiceInterface {

    @Override
    public String hello(String s) {
        return "Hello " + s + "!";
    }

}
