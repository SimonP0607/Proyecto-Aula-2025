package co.edu.upb.Proyecto_Aula2025;

public class Edificio {

	private String nombre;
    private int id;

    public Edificio(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
    	return nombre; 
    
    }
    
    public int getId() { 
    	return id;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
