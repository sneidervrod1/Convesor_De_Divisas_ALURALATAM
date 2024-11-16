/** @author Sneider */

package aluraconversordemonedas;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Scanner;

public class AluraConversorDeMonedas {

    public static void main(String[] args) throws IOException, InterruptedException {
        int option = -1;
        boolean exit = false;
        String monedas = "";

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting()
                .create();
        System.out.println(" BIENVENIDO AL CONVERTIDOR DE MONEDAS");
        while (!exit) {
            
            System.out.println(" --------------- MENU ---------------");
            System.out.println("[1] - DOLAR  =======>  PESO ARGENTINO");
            System.out.println("[2] - PESO ARGENTINO  =======>  DOLAR");
            System.out.println("[3] - DOLAR  =======>  REAL BRASILERO");
            System.out.println("[4] - REAL BRASILERO  =======>  DOLAR");
            System.out.println("[5] - DOLAR  =======> PESO COLOMBIANO");
            System.out.println("[6] - PESO COLOMBIANO  =======> DOLAR");
            System.out.println("[7] - SALIR");
            System.out.print("Digite una opcion: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    monedas = "USD/ARS";

                    break;
                case 2:
                    monedas = "ARS/USD";

                    break;
                case 3:
                    monedas = "USD/BRL";

                    break;
                case 4:
                    monedas = "BRL/USD";

                    break;
                case 5:
                    monedas = "USD/COP";

                    break;
                case 6:
                    monedas = "COP/USD";

                    break;
                    
                case 7:
                    System.out.println("ADIOS !!!!!!!!!!!!!!!!!!!");
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Opcion no valida. Por favor, intenta de nuevo.");

                    break;

            }
            if(option == 1 ||  option == 2 || option == 3 ||  option == 4 ||  option == 5 || option == 6 ){
                Conversor conversorDefault = new Conversor();

                String x = conversorDefault.ConversordeMonedas(conversorDefault.createDir(monedas));
//              System.out.println("DATOS DEL JSON: " + x);
                ConversorERAPI conversorErapi = gson.fromJson(x, ConversorERAPI.class);
//              System.out.println("DATOS DEL RECORD:" + conversorErapi);
                System.out.print("Ingrese el valor que desea convertir: ");
            
                double cantidad = scanner.nextInt();
                Conversor miConversor = new Conversor(conversorErapi);
                System.out.println(miConversor.total(cantidad)); 
            
            }
        }
    }
}
