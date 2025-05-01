package co.edu.upb.Proyecto_Aula2025;

public class Aristas {
    private Edificio origen;
    private Edificio destino;
    private double distancia;
    private boolean tieneEscaleras;
    private boolean activo;

    public Aristas(Edificio origen, Edificio destino, double distancia, boolean tieneEscaleras, boolean activo) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.tieneEscaleras = tieneEscaleras;
        this.activo = activo;
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

    public boolean tieneEscaleras() {
        return tieneEscaleras;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return origen.getNombre() + " -> " + destino.getNombre() + " (" + distancia + "m, " +
               (tieneEscaleras ? "escaleras" : "accesible") + ", " +
               (activo ? "activo" : "inactivo") + ")";
    }
}

