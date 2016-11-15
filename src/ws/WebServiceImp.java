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

    @Override
    public String searchMultiple(String arg0, String arg1, String arg2, String arg3, String arg4) {
        return Search.searchByMultiple(arg0, arg1, arg2, arg3, arg4);
    }
}
