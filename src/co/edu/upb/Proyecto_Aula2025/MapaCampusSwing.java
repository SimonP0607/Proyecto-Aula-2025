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
        
     // panelContenedor.add(scrollResultado, BorderLayout.CENTER);
        panelContenedor.add(panelVisual, BorderLayout.CENTER);
        
        add(panelContenedor);
        
        btnCalcular.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		Edificio origen = (Edificio) comboOrigen.getSelectedItem();
        		Edificio destino = (Edificio) comboDestino.getSelectedItem();
        		boolean evitarEscaleras = chkEvitarEscaleras.isSelected();
        		
        		String resultado = mapa.buscarRutaMasCorta(origen, destino, evitarEscaleras);
        		resultadoArea.setText(resultado);
        	}
        }
        );
    }
    
    private void inicializarMapa() {
    
	    Edificio a = new Edificio("A", 0, 205, 148);
	    Edificio b = new Edificio("B", 1, 205, 185);
	    Edificio c = new Edificio("C", 2, 205, 238);
	    Edificio d = new Edificio("D", 3, 310, 242);
	    Edificio e = new Edificio("E", 4, 370, 320);
	    Edificio f = new Edificio("F", 5, 630, 230);
	    Edificio g = new Edificio("G", 6, 595, 275);
	    Edificio h = new Edificio("H", 7, 635, 320);
	    Edificio i = new Edificio("I", 8, 570, 360);
	    Edificio j = new Edificio("J", 9, 700, 370);
	    Edificio k = new Edificio("K", 10, 290, 290);
	    Edificio l = new Edificio("L", 11, 760, 120);
	    Edificio p1 = new Edificio("P1", 12, 740, 245);
	    Edificio cf = new Edificio("CF", 13, 260, 210);
	
	    Edificio[] edificios = {a, b, c, d, e, f, g, h, i, j, k, l, p1, cf};
	    for (Edificio ed : edificios) mapa.agregarEdificio(ed); //AGREGA EDIFICIOS AL MAPA
	
	    mapa.agregarArista(p1, c, 30, false, true);
	    mapa.agregarArista(p1, j, 30, false, true);
	    mapa.agregarArista(a, b, 10, false, true);
	    mapa.agregarArista(b, cf, 15, true, true);
	    mapa.agregarArista(b, c, 10, false, true);
	    mapa.agregarArista(c, cf, 15, false, true);
	    mapa.agregarArista(c, j, 15, false, true);
	    mapa.agregarArista(cf, d, 30, false, true);
	    mapa.agregarArista(d, g, 25, false, true);
	    mapa.agregarArista(d, j, 40, true, true);
	    mapa.agregarArista(d, k, 250, false, true);
	    mapa.agregarArista(d, e, 40, false, true);
	    mapa.agregarArista(e, f, 10, false, true);
	    mapa.agregarArista(e, i, 60, false, true);
	    mapa.agregarArista(f, g, 20, false, true);
	    mapa.agregarArista(f, h, 15, false, true);
	    mapa.agregarArista(h, i, 80, false, true);
	    mapa.agregarArista(h, l, 110, true, true);
	    mapa.agregarArista(i, k, 100, false, true);
	    mapa.agregarArista(i, l, 10, false, true);
	    mapa.agregarArista(k, l, 15, false, true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MapaCampusSwing().setVisible(true);
        });
    }
}





