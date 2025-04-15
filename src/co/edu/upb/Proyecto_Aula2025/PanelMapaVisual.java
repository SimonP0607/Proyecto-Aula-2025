package co.edu.upb.Proyecto_Aula2025;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelMapaVisual extends JPanel {
    private MapaCampus mapa;
    private Map<Edificio, Point> posiciones;

    public PanelMapaVisual(MapaCampus mapa) {
        this.mapa = mapa;
        this.posiciones = new HashMap<>();
        asignarPosiciones();
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    private void asignarPosiciones() {
        // Ubicaciones manuales estilo mapa de pizarra
        for (Edificio e : mapa.obtenerTodosLosEdificios()) {
            switch (e.getNombre()) {
                case "A" -> posiciones.put(e, new Point(100, 400));
                case "B" -> posiciones.put(e, new Point(200, 400));
                case "C" -> posiciones.put(e, new Point(300, 400));
                case "CF" -> posiciones.put(e, new Point(250, 300));
                case "D" -> posiciones.put(e, new Point(400, 300));
                case "E" -> posiciones.put(e, new Point(450, 250));
                case "F" -> posiciones.put(e, new Point(500, 300));
                case "G" -> posiciones.put(e, new Point(550, 300));
                case "H" -> posiciones.put(e, new Point(550, 250));
                case "I" -> posiciones.put(e, new Point(500, 150));
                case "J" -> posiciones.put(e, new Point(400, 400));
                case "K" -> posiciones.put(e, new Point(450, 50));
                case "L" -> posiciones.put(e, new Point(600, 150));
                case "P1" -> posiciones.put(e, new Point(350, 500));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        // Dibujar caminos
        for (Edificio origen : mapa.obtenerTodosLosEdificios()) {
            List<Camino> caminos = mapa.obtenerCaminosDesde(origen);
            Point puntoOrigen = posiciones.get(origen);

            for (Camino camino : caminos) {
                Edificio destino = camino.getDestino();
                Point puntoDestino = posiciones.get(destino);

                if (puntoDestino == null || puntoOrigen == null) continue;

                // Color según estado
                if (!camino.estaActivo()) {
                    g2.setColor(Color.RED);
                } else if (camino.tieneEscaleras()) {
                    g2.setColor(Color.ORANGE);
                } else {
                    g2.setColor(Color.BLACK);
                }

                g2.drawLine(puntoOrigen.x, puntoOrigen.y, puntoDestino.x, puntoDestino.y);

                // Etiqueta de distancia
                int mx = (puntoOrigen.x + puntoDestino.x) / 2;
                int my = (puntoOrigen.y + puntoDestino.y) / 2;
                g2.setColor(Color.BLUE);
                g2.drawString(camino.getDistancia() + "m", mx, my);
            }
        }

        // Dibujar nodos
        for (Edificio edificio : mapa.obtenerTodosLosEdificios()) {
            Point p = posiciones.get(edificio);
            if (p == null) continue;

            g2.setColor(Color.CYAN);
            g2.fillOval(p.x - 15, p.y - 15, 30, 30);
            g2.setColor(Color.BLACK);
            g2.drawOval(p.x - 15, p.y - 15, 30, 30);
            g2.drawString(edificio.getNombre(), p.x - 5, p.y + 5);
        }
    }
}
