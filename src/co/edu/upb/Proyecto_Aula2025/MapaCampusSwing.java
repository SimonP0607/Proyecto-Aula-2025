package co.edu.upb.Proyecto_Aula2025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MapaCampusSwing extends JFrame {

	private static final long serialVersionUID = 1L;
	private MapaCampus mapa;
    private JComboBox<Edificio> comboOrigen;
    private JComboBox<Edificio> comboDestino;
    private JCheckBox chkEvitarEscaleras;
    private JTextArea resultadoArea;
    private JTextArea textoRuta;

    public MapaCampusSwing() {
        setTitle("Mapa Interactivo del Campus");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mapa = new MapaCampus();
        inicializarMapa();
        
        PanelMapaVisual panelVisual = new PanelMapaVisual(mapa);


        JPanel panelContenedor = new JPanel(new BorderLayout());
        JPanel panelControles = new JPanel();
        
        
        comboOrigen = new JComboBox<>();
        comboDestino = new JComboBox<>();
        chkEvitarEscaleras = new JCheckBox("Evitar escaleras");
        JButton btnCalcular = new JButton("Calcular Ruta");
        
        resultadoArea = new JTextArea(10,40);
        resultadoArea.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(resultadoArea);
        
        Edificio[] disponibles = mapa.obtenerTodosLosEdificios();
        
        for (int e1 = 0; e1 < disponibles.length; e1++) {
        	comboOrigen.addItem(disponibles[e1]);
        	comboDestino.addItem(disponibles[e1]);
        }
        
        panelControles.add(new JLabel("Origen:"));
        panelControles.add(comboOrigen);
        
        panelControles.add(new JLabel("Destino:"));
        panelControles.add(comboDestino);
        
        panelControles.add(chkEvitarEscaleras);
        panelControles.add(btnCalcular);
        
        panelContenedor.add(panelControles, BorderLayout.NORTH);
        panelContenedor.add(scrollResultado, BorderLayout.CENTER);
        
        panelContenedor.add(panelVisual, BorderLayout.CENTER);
        
        add(panelContenedor);
        
        btnCalcular.addActionListener(e -> {
            Edificio origen = (Edificio) comboOrigen.getSelectedItem();
            Edificio destino = (Edificio) comboDestino.getSelectedItem();
            boolean evitarEscaleras = chkEvitarEscaleras.isSelected();

            Edificio[] camino = mapa.buscarRutaMasCorta(origen, destino, evitarEscaleras);  
            panelVisual.setRuta(camino);  // Lo pasamos a dibujar
            
            StringBuilder sb = new StringBuilder();
            sb.append("Ruta: ");

            for (int i = 0; i < camino.length; i++) {
                sb.append(camino[i].getNombre());
                if (i < camino.length - 1) {
                    sb.append(" → ");
                }
            }

            // (Mostrar distancia total
            double total = mapa.getDistanciaDeRuta(camino);
            sb.append("\nDistancia total: ").append((int) total).append(" m");

            textoRuta.setText(sb.toString());
        });
        
        textoRuta = new JTextArea(3, 40);
        textoRuta.setEditable(false);
        textoRuta.setLineWrap(true);
        textoRuta.setWrapStyleWord(true);

        JScrollPane scrollTexto = new JScrollPane(textoRuta);
        panelContenedor.add(scrollTexto, BorderLayout.SOUTH);
    }
    
    private void inicializarMapa() {
    
    	Edificio a = new Edificio("A", 0, 0.2500, 0.3000);
    	Edificio b = new Edificio("B", 1, 0.2900, 0.3700);
    	Edificio c = new Edificio("C", 2, 0.2500, 0.4700);
    	Edificio d = new Edificio("D", 3, 0.39, 0.5);
    	Edificio e = new Edificio("E", 4, 0.47, 0.62);
    	Edificio f = new Edificio("F", 5, 0.43, 0.72);
    	Edificio g = new Edificio("G", 6, 0.42, 0.83);
    	Edificio h = new Edificio("H", 7, 0.54, 0.76);
    	Edificio i = new Edificio("I", 8, 0.8, 0.65);
    	Edificio j = new Edificio("J", 9, 0.230, 0.62);
    	Edificio k = new Edificio("K", 10, 0.8000, 0.3000);
    	Edificio l = new Edificio("L", 11, 0.9, 0.8);
    	Edificio p1 = new Edificio("P1", 12, 0.13, 0.45);
    	Edificio cf = new Edificio("CF", 13, 0.33, 0.44);
	
	    Edificio[] edificios = {a, b, c, d, e, f, g, h, i, j, k, l, p1, cf};
	    for (Edificio ed : edificios) mapa.agregarEdificio(ed); //AGREGA EDIFICIOS AL MAPA
	
	    mapa.agregarArista(p1, c, 51.48, false, true);
	    mapa.agregarArista(p1, j, 48.82, false, true);
	    mapa.agregarArista(a, b, 17.97, false, true);
	    mapa.agregarArista(b, cf, 21.2, true, true);
	    mapa.agregarArista(b, c, 26.93, false, true);
	    mapa.agregarArista(c, j, 104.07, false, true);
	    mapa.agregarArista(cf, d, 30.35, false, true);
	    mapa.agregarArista(d, g, 74.81, false, true);
	    mapa.agregarArista(d, j, 61.02, true, true);
	    mapa.agregarArista(d, k, 136.22, false, true);
	    mapa.agregarArista(d, e, 66.26, false, true);
	    mapa.agregarArista(e, f, 14.6, false, true);
	    mapa.agregarArista(e, i, 106.14, false, true);
	    mapa.agregarArista(f, g, 16.33, false, true);
	    mapa.agregarArista(f, h, 34.27, false, true);
	    mapa.agregarArista(h, i, 119.27, false, true);
	    mapa.agregarArista(h, l, 163.68, true, true);
	    mapa.agregarArista(i, k, 138.99, false, true);
	    mapa.agregarArista(i, l, 44.11, false, true);
	    mapa.agregarArista(k, l, 166.52, false, true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MapaCampusSwing().setVisible(true);
        });
    }
}





