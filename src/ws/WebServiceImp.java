package ws;

import elastic.Search;

import javax.jws.WebService;

/**
 * Created by zsibritajanos on 2016.01.20..
 */
@WebService(endpointInterface = "ws.WebServiceInterface")
public class WebServiceImp implements WebServiceInterface {

    @Override
    public String hello(String s) {
        return Search.searchByLemma(s);
    }

    @Override
    public String searchByDep(String dep) {
        return Search.searchByDep(dep);
    }
}
