package co.edu.upb.Proyecto_Aula2025;

public class Camino {

	private Edificio origen;
    private Edificio destino;
    private double distancia;

    public Camino(Edificio origen, Edificio destino, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public Edificio getOrigen() { 
    	return origen; 
    }
    
    public Edificio getDestino() {
    	return destino; 
    }
    
    public double getDistancia() { 
    	return distancia; 
    }
}
