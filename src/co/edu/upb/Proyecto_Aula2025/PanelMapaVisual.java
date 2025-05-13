package co.edu.upb.Proyecto_Aula2025;

import javax.swing.*;
import java.awt.*;


public class PanelMapaVisual extends JPanel {
	private MapaCampus mapa;
    private Image fondoMapa;
    private final int anchoBase = 1894;
    private final int altoBase = 790;
    private Edificio[] caminoCalculado;

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
    	
    	// Calcular escala para mantener relación de aspecto
    	double escalaX = (double) getWidth() / anchoBase;
    	double escalaY = (double) getHeight() / altoBase;
    	double escala = Math.min(escalaX, escalaY);

    	// Calcular tamaño final de la imagen
    	int imagenAncho = (int)(anchoBase * escala);
    	int imagenAlto = (int)(altoBase * escala);

    	// Centrar la imagen
    	int offsetX = (getWidth() - imagenAncho) / 2;
    	int offsetY = (getHeight() - imagenAlto) / 2;

    	// Dibujar la imagen centrada y escalada
    	g.drawImage(fondoMapa, offsetX, offsetY, imagenAncho, imagenAlto, this);

    	// Ahora dibujar los nodos encima de la imagen
    	Edificio[] edificios = mapa.obtenerTodosLosEdificios();
        int cantidad = mapa.getCantidadEdificios();

        for (int i = 0; i < cantidad; i++) {
            Edificio e = edificios[i];

            int x = offsetX + (int)(e.getXRelativo() * imagenAncho);
            int y = offsetY + (int)(e.getYRelativo() * imagenAlto);

            g.setColor(Color.RED);
            g.fillOval(x - 10, y - 10, 20, 20);
            g.setColor(Color.BLACK);
            g.drawOval(x - 10, y - 10, 20, 20);
        	g.drawString(e.getNombre(), x, y);
        }
        
     // DIBUJAR ARISTAS DE LA RUTA CALCULADA
        if (caminoCalculado != null && caminoCalculado.length > 1) {
            g.setColor(Color.BLUE);
            for (int i = 0; i < caminoCalculado.length - 1; i++) {
                Edificio origen = caminoCalculado[i];
                Edificio destino = caminoCalculado[i + 1];

                int x1 = offsetX + (int)(origen.getXRelativo() * imagenAncho);
                int y1 = offsetY + (int)(origen.getYRelativo() * imagenAlto);
                int x2 = offsetX + (int)(destino.getXRelativo() * imagenAncho);
                int y2 = offsetY + (int)(destino.getYRelativo() * imagenAlto);

                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
    
    public void setRuta(Edificio[] camino) {
        this.caminoCalculado = camino;
        repaint(); // Redibuja para mostrar el nuevo camino
    }
}
