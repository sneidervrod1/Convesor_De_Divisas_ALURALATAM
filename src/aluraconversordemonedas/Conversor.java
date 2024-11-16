/** @author Sneider*/

package aluraconversordemonedas;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class Conversor {
    private String moneda1;
    private String moneda2;
    private double conversionRate;
    
    private String generalDirection = "https://v6.exchangerate-api.com/v6/842c5c23c3a58d512bcf103f/pair/";

    public Conversor() {
        
    }

    
    public String createDir(String monedas){
        
        return generalDirection + monedas;
    }    
    
    public String ConversordeMonedas(String direction) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direction)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.body();
        
    }

    public Conversor(ConversorERAPI conversor) {
        this.moneda1 = conversor.base_code();
        this.moneda2 = conversor.target_code();
        this.conversionRate = conversor.conversion_rate();
    }
    
    public String total(double cantidad){
        String formated = String.format("%.2f", (cantidad * conversionRate));
        return "El valor " + cantidad + "[" + moneda1 + "]"+ " corresponde al valor final de: " + formated + "[" + moneda2 + "]" ;
    }
}
