package co.edu.upb.Proyecto_Aula2025;

import java.util.*;

public class MapaCampus {

	private Map<Edificio, List<Camino>> conexionesEdificios = new HashMap<>();

    // Agregar un edificio al mapa
    public void agregarEdificio(Edificio edificio) {
        conexionesEdificios.putIfAbsent(edificio, new ArrayList<>());
    }

    // Agregar un camino entre edificios
    public void agregarCamino(Edificio origen, Edificio destino, double distancia) {
        conexionesEdificios.get(origen).add(new Camino(origen, destino, distancia));
        conexionesEdificios.get(destino).add(new Camino(destino, origen, distancia)); // Bidireccional
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
