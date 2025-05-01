package co.edu.upb.Proyecto_Aula2025;

public class MapaCampus {

	private Edificio[] edificios ;
	private Aristas[][] matrizAdyacencia;
	private int cantidadEdificios;
	
	
	public MapaCampus() {
		edificios = new Edificio[100];
		matrizAdyacencia = new Aristas[100][100];
		cantidadEdificios = 0;
	}

	//AGREGAR UN EDIFICIO AL MAPA
    public void agregarEdificio(Edificio edificio) {
        if (cantidadEdificios < edificios.length) {
        	edificios[cantidadEdificios] = edificio;
        	cantidadEdificios++;
        } else {
        	System.out.println("No hay más cupos");
        }
    }

    //AGREGAR LA ARISTA ENTRE EDIFICIOS
    public void agregarArista(Edificio origen, Edificio destino, double distancia, boolean tieneEscaleras, boolean activo) {
        if (!edificioExiste(origen)) {
        	agregarEdificio(origen);
        }
        if(!edificioExiste(destino)) {
        	agregarEdificio(destino);
        }
        
        int idOrigen = origen.getId();
        int idDestino = destino.getId();
        
        matrizAdyacencia[idOrigen][idDestino] = new Aristas(origen, destino, distancia, tieneEscaleras, activo);
        matrizAdyacencia[idDestino][idOrigen] = new Aristas(destino, origen, distancia, tieneEscaleras, activo);
    }
    
    //COMPROBAR QUE EL EDIFICIO EXISTA
    private boolean edificioExiste(Edificio edificio) {
        for (int i = 0; i < cantidadEdificios; i++) {
            if (edificios[i].getId() == edificio.getId()) {
                return true;
            }
        }
        return false;
    }
    

    //Busca la ruta mas corta entre edificios
    public String buscarRutaMasCorta(Edificio origen, Edificio destino, boolean evitarEscaleras) {
       double[] distancias = new double[cantidadEdificios];
       boolean[] visitados = new boolean[cantidadEdificios];
       int[] anteriores = new int[cantidadEdificios];
       String resultado = "";//CONTRUCCIÖN DEL CAMINO
       
       
       for (int i = 0; i < cantidadEdificios; i++) {
    	   distancias[i] = Double.POSITIVE_INFINITY;
    	   visitados[i]= false;
    	   anteriores[i] = -1;
       }
       
       int idOrigen = origen.getId();
       distancias[idOrigen] = 0;
       
     //1. BUSCAR EL NODO NO VISITADO CON MENOR DISTANCIA
       for (int paso = 0; paso < cantidadEdificios; paso++) {
    	   int actual = -1;
    	   double menorDistancia = Double.POSITIVE_INFINITY;
    	   
    	   for (int i = 0; i < cantidadEdificios; i++) {
    		   if (!visitados[i] && distancias[i] < menorDistancia) {
    			   menorDistancia = distancias[i];
    			   actual = i;
    		   }
    	   }
    	   
    	   if(actual == -1) break; //NO HAY MÁS ALCANZABLES
    	   
    	   //2. MARCAR COMO VISTADO
    	   visitados[actual] = true;
    	   
    	   for (int vecino = 0; vecino < cantidadEdificios; vecino++) {
    		   Aristas arista = matrizAdyacencia[actual][vecino];
    		   
    		   if (arista !=null && arista.estaActivo()) {
    			   if(evitarEscaleras && arista.tieneEscaleras()) {
    				   continue; //SI SE QUIERE EVITAR ESCALERAS, SE SALTA LA ARISTA ACTUAL
    			   }
    			   
    			   double nuevaDistancia = distancias[actual] + arista.getDistancia();
    			   
    			   if(nuevaDistancia < distancias[vecino]) {
    				   distancias[vecino] = nuevaDistancia;
    				   anteriores[vecino] = actual; //ACTUAL ES EL NODO ANTERIOR AL VECINO
    			   }
    		   }
    	   }
    	   
       }
       
       int idDestino = destino.getId();
       if(distancias[idDestino] == Double.POSITIVE_INFINITY) {
    	   resultado += "No hay ruta disponible desde "+ origen.getNombre() + " hasta " + destino.getNombre();
    	   return resultado;
       }
       
       int actual = idDestino;
       String ruta = "";
       
       while (actual != -1) {
    	   ruta = edificios[actual].getNombre() + " -> " + ruta;
    	   actual = anteriores[actual];
       }
       
       resultado += "Ruta más corta: " + ruta + "\\n";
       resultado += "Distancia total: " + distancias[idDestino] + " metros\\n";
       
       return resultado;
    }


    // Obtener los caminos de un edificio
    public Aristas[] obtenerAristasDesde(Edificio edificio) {
         int id = edificio.getId();
         Aristas[] adyacentes = new Aristas[cantidadEdificios];
         
         for (int i = 0; i < cantidadEdificios; i++) {
        	 adyacentes[i] = matrizAdyacencia[id][i];
         }
         
         return adyacentes;
    }

    // Obtener todos los edificios del campus
    public Edificio[] obtenerTodosLosEdificios() {
        Edificio [] resultado = new Edificio[cantidadEdificios];
        
        for(int i = 0; i < cantidadEdificios; i++) {
        	resultado[i] = edificios[i];
        }
        
        return resultado;
    }
}





























