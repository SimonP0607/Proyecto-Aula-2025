package co.edu.upb.Proyecto_Aula2025;

public class Edificio {

	private String nombre;
    private int id;
    private double xRelativo;
    private double  yRelativo;

    

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
    
    public double getXRelativo() {
        return xRelativo;
    }

    public double getYRelativo() {
        return yRelativo;
    }

	@Override
    public String toString() {
        return "Edifcio "+nombre; 
    }
}
