package co.edu.upb.Proyecto_Aula2025;

public class Edificio {

	private String nombre;
    private int id;
<<<<<<< HEAD
    private double xRelativo;
    private double  yRelativo;

    
=======
    private int x;
    private int y;

    public Edificio(String nombre, int id, int x, int y) {
        this.nombre = nombre;
        this.id = id;
        this.x = x;
        this.y = y;
    }
>>>>>>> 0ed93380edb78f85d1bd21757ac8e6bdd447f78b

    public Edificio(String nombre, int id, double xRelativo, double yRelativo) {
		this.nombre = nombre;
		this.id = id;
		this.xRelativo = xRelativo;
		this.yRelativo = yRelativo;
	}

	public String getNombre() {
    	return nombre; 
    
    }
    
    public int getId() { 
    	return id;
    }
    
<<<<<<< HEAD
    public double getXRelativo() {
        return xRelativo;
    }

    public double getYRelativo() {
        return yRelativo;
    }
=======
    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
>>>>>>> 0ed93380edb78f85d1bd21757ac8e6bdd447f78b

	@Override
    public String toString() {
        return "Edifcio "+nombre; 
    }
}
