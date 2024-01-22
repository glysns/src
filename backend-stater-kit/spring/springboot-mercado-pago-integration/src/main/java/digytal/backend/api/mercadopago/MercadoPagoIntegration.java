package digytal.backend.api.mercadopago;

import digytal.backend.api.properties.CadastroProperties;
import digytal.backend.api.model.cliente.ClienteRequest;
import digytal.backend.api.properties.MercadoPagoCredencial;
import digytal.backend.api.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MercadoPagoIntegration {
    @Autowired
    private ClienteService service;

    @Autowired
    private CadastroProperties cadastroProperties;

    @Autowired
    private MercadoPagoCredencial credencial;

    private final String URL = "https://api.mercadopago.com" ;
    public void listarPlanos(){

        RestTemplate client = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(headers());
        ResponseEntity<String> result = client.exchange(URL.concat("/preapproval_plan/"+ credencial.getPlanId()), HttpMethod.GET,entity, String.class);

        System.out.println(result);
    }

    public void listarAssinaturas(){

        RestTemplate client = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(headers());
        ResponseEntity<String> result = client.exchange(URL.concat("/preapproval/search"), HttpMethod.GET,entity, String.class);

        System.out.println(result);
    }

    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();

        System.out.println("TOKEN -> " + credencial.getAccessToken());

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+credencial.getAccessToken());

        return headers;
    }
    public void execute(){
        ClienteRequest request = new ClienteRequest();
        BeanUtils.copyProperties(cadastroProperties,request);
        System.out.println(request);

        listarPlanos();
    }
}
