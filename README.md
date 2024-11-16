# Conversor de DIVISAS - Proyecto practico del curso **Java Orientado a Objetos G7 - ONE** 

"La formación Java Orientado a Objetos de Alura es una guia de aprendizaje completo para que inicies una carrera en programación Java. Aprenderás desde los fundamentos del lenguaje con buenas prácticas hasta el conocimiento esencial para un buen desarrollo orientado a objetos."

Este proyecto es la culmunacion del curso anteriormente mencionado, donde puse a prueba todo lo aprendido.

**Nota:** Para llevar a cabo este proyecto fue de suma importancia seguir la guia en TRELLO, que fue brindada por ALURA-LATAM, ademas de las instrucciones dadas en la explicación del desafio.

## Utilice la API [Exchange Rate - API](https://www.exchangerate-api.com/)

## Defini tres clases para la resolucion del proyecto 
```
AluraConversorDeMonedas.java
Conversor.java
ConversorERAPI.java
```
## AluraConversorDeMonedas.java

Clase principal o MAIN del proyecto donde podemos encontrar el MENU del Proyecto y la logica detras de la creacion de los Ojetos *Conversor.java* la clase donde se encuentra toda la logica del proyecto.

Desde el MAIN solo llamamos a los metodos de Conversor y Ejecutamos el LOOP hasta que la condición se deje de cumplir
```
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
                    System.out.println("Opcion no valida. Por favor, intenta de nuevo.");S
                    break;
            }
            if(option == 1 ||  option == 2 || option == 3 ||  option == 4 ||  option == 5 || option == 6 ){
                Conversor conversorDefault = new Conversor();
                String x = conversorDefault.ConversordeMonedas(conversorDefault.createDir(monedas));
                ConversorERAPI conversorErapi = gson.fromJson(x, ConversorERAPI.class);

                System.out.print("Ingrese el valor que desea convertir: ");
                double cantidad = scanner.nextInt();

                Conversor miConversor = new Conversor(conversorErapi);
                System.out.println(miConversor.total(cantidad)); 
            }
```
## Conversor.java

Desde esta clase se definio toda la logica detras del Proyecto 

### public Conversor(ConversorERAPI conversor), public Conversor()   
Contamos con dos Metodos *Constructores* , Uno sin parametros y otro que se Crea a Partir de *ConversorERAPI*, para guardar los datos recolectados del JSON
```
public Conversor() {
}

public Conversor(ConversorERAPI conversor) {
        this.moneda1 = conversor.base_code();
        this.moneda2 = conversor.target_code();
        this.conversionRate = conversor.conversion_rate();
    }
```
### createDir()

En este metodo definimos la URL a Conslutar por medio de Concatenacion de un String que se mantiene + las divisas que se van a consultar para asi obtener la direccion que siempre buscamos sin tener que crear tantos Strings como URL necesitemos

```
    private String generalDirection = "https://v6.exchangerate-api.com/v6/842c5c23c3a58d512bcf103f/pair/";
    public String createDir(String monedas){   
        return generalDirection + monedas;
    }    
```
###  ConversordeMonedas()

Desde aqui se crea el *CLIENTE, REQUEST Y RESPONSE*, Se llama a *createDir()* para obtener la URL que buscar y devolver el *response.body()*
```
    public String ConversordeMonedas(String direction) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direction)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
```
###  Total()

Desde aqui se hace la operacion para obtener el valor convertido.
```
    public String total(double cantidad){
        String formated = String.format("%.2f", (cantidad * conversionRate));
        return "El valor " + cantidad + "[" + moneda1 + "]"+ " corresponde al valor final de: " + formated + "[" + moneda2 + "]" ;
    }
```

## ConversorERAPI.java

Esta clase fue utilizada como tipo de *Clase RECORD* o *DTO(Data trasnfer Object)*, y solo se definio los datos que queria recolectar del JSON o RESPONSE para poder trabajarlos mas adelante despues de pasarlos a la clase *Conversor.java* por medio de un constructor definido en la clase *Conversor.java*
```
public record ConversorERAPI(String base_code, String target_code, double conversion_rate) {
}
```
