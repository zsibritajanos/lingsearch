package ws.client;

import ws.WebServiceInterface;
import ws.WebServiceImpService;

/**
 * Created by zsibritajanos on 2016.01.31..
 */
public class WebServiceClient {
    public static void main(String[] args) {
        WebServiceImpService webServiceImpService = new WebServiceImpService();
        WebServiceInterface webServiceInterface = webServiceImpService.getWebServiceImpPort();
        webServiceInterface.hello("Janos");
    }
}