package co.edu.upb.Proyecto_Aula2025;

public class Edificio {

	private String nombre;
    private int id;
    private int x;
    private int y;

    public Edificio(String nombre, int id, int x, int y) {
        this.nombre = nombre;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
    	return nombre; 
    
    }
    
    public int getId() { 
    	return id;
    }
    
    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
    public String toString() {
        return "Edifcio "+nombre; 
    }
}
