package co.edu.upb.Proyecto_Aula2025;

import java.util.*;

public class MapaCampus {

	private Map<Edificio, List<Camino>> conexionesEdificios;
	
    public MapaCampus() {
		this.conexionesEdificios = new HashMap<>();
	}

	// Agregar un edificio al mapa
    public void agregarEdificio(Edificio edificio) {
        conexionesEdificios.put(edificio, new ArrayList<>());
    }

    // Agregar un camino entre edificios
    public void agregarCamino(Edificio origen, Edificio destino, double distancia, boolean tieneEscaleras, boolean activo) {
        if (!conexionesEdificios.containsKey(origen)) {
            agregarEdificio(origen);
        }
        if (!conexionesEdificios.containsKey(destino)) {
        	agregarEdificio(destino);
        }

        conexionesEdificios.get(origen).add(new Camino(origen, destino, distancia, tieneEscaleras, activo));
        conexionesEdificios.get(destino).add(new Camino(destino, origen, distancia, tieneEscaleras, activo));
    }
    
    //Imprime el mapa del campus
    public void imprimirMapaCampus() {
        List<Edificio> edificios = new ArrayList<>(conexionesEdificios.keySet());
        
        for (int i = 0; i < edificios.size(); i++) {
            Edificio edificio = edificios.get(i);
            System.out.print(edificio.getNombre() + " -> ");
            
            List<Camino> caminos = conexionesEdificios.get(edificio);
            for (int j = 0; j < caminos.size(); j++) {
                Camino camino = caminos.get(j);
                System.out.print(camino.getDestino().getNombre() + " (" + camino.getDistancia() + " m)  ");
            }

            System.out.println();
        }
    }

    //Busca la ruta mas corta entre edificios
    public void buscarRutaMasCorta(Edificio origen, Edificio destino, boolean evitarEscaleras) {
        List<Edificio> edificios = new ArrayList<>(conexionesEdificios.keySet());
        int n = edificios.size();

        double[] distancias = new double[n];
        boolean[] visitados = new boolean[n];
        int[] camino = new int[n];

        for (int i = 0; i < n; i++) {
            distancias[i] = Double.MAX_VALUE;
            camino[i] = -1;
        }

        distancias[edificios.indexOf(origen)] = 0;

        while (true) {
            boolean hayNoVisitados = false;
            for (int i = 0; i < visitados.length; i++) {
                if (!visitados[i]) {
                    hayNoVisitados = true;
                    break;
                }
            }
            if (!hayNoVisitados) break;

            int actual = -1;
            double menorDistancia = Double.MAX_VALUE;
            for (int i = 0; i < distancias.length; i++) {
                if (!visitados[i] && distancias[i] < menorDistancia) {
                    menorDistancia = distancias[i];
                    actual = i;
                }
            }

            if (actual == -1) break;

            visitados[actual] = true;
            Edificio edificioActual = edificios.get(actual);
            List<Camino> vecinos = conexionesEdificios.get(edificioActual);

            for (int i = 0; i < vecinos.size(); i++) {
                Camino caminoActual = vecinos.get(i);
                if (!caminoActual.estaActivo()) continue;
                if (evitarEscaleras && caminoActual.tieneEscaleras()) continue;

                Edificio vecino = caminoActual.getDestino();
                int idxVecino = edificios.indexOf(vecino);
                double nuevaDistancia = distancias[actual] + caminoActual.getDistancia();
                if (!visitados[idxVecino] && nuevaDistancia < distancias[idxVecino]) {
                    distancias[idxVecino] = nuevaDistancia;
                    camino[idxVecino] = actual;
                }
            }
        }

        int destinoIndex = edificios.indexOf(destino);
        if (distancias[destinoIndex] == Double.MAX_VALUE) {
            System.out.println("No hay ruta entre " + origen.getNombre() + " y " + destino.getNombre());
            return;
        }

        System.out.println("La distancia más corta desde " + origen.getNombre() + " hasta " + destino.getNombre() + " es: " + distancias[destinoIndex] + " metros");

        List<Edificio> ruta = new ArrayList<>();
        int actual = destinoIndex;
        while (actual != -1) {
            ruta.add(edificios.get(actual));
            actual = camino[actual];
        }
        Collections.reverse(ruta);

        System.out.print("Ruta: ");
        for (int i = 0; i < ruta.size(); i++) {
            System.out.print(ruta.get(i).getNombre() + " -> ");
        }
        System.out.println("FIN");
    }


    // Obtener los caminos de un edificio
    public List<Camino> obtenerCaminosDesde(Edificio edificio) {
        return conexionesEdificios.get(edificio);
    }

    // Obtener todos los edificios del campus
    public Set<Edificio> obtenerTodosLosEdificios() {
        return conexionesEdificios.keySet();
    }
}
