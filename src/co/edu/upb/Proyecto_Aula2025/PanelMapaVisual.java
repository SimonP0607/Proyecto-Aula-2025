package co.edu.upb.Proyecto_Aula2025;

import javax.swing.*;
import java.awt.*;

public class PanelMapaVisual extends JPanel {

    private MapaCampus mapa;
    private Image fondoMapa;

    // Constructor
    public PanelMapaVisual(MapaCampus mapa) {
        this.mapa = mapa;

        // Cargar la imagen de fondo desde recursos
        fondoMapa = new ImageIcon(getClass().getResource("/recursos/MAPA_UPB_2025.png")).getImage();
    }

    // Dibujo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar imagen de fondo al tamaño del panel
        g.drawImage(fondoMapa, 0, 0, getWidth(), getHeight(), this);
        
        Edificio[] edificios = mapa.obtenerTodosLosEdificios();
        
        for (int i = 0; i < mapa.getCantidadEdificios(); i++) {
        	Edificio e = edificios[i];
        	int x = e.getX();
        	int y = e.getY();
        	
        	g.setColor(Color.CYAN);
        	g.fillOval(x, y, 20, 20);
        	g.setColor(Color.BLACK);
        	g.drawOval(x, y, 20, 20);
        	
        	g.drawString(e.getNombre(), x, y);
        }
    }
    
    
    
}