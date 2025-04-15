package co.edu.upb.Proyecto_Aula2025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MapaCampusSwing extends JFrame {
    private MapaCampus mapa;
    private JComboBox<Edificio> comboOrigen;
    private JComboBox<Edificio> comboDestino;
    private JCheckBox chkEvitarEscaleras;
    private JTextArea resultadoArea;

    public MapaCampusSwing() {
        setTitle("Mapa Interactivo del Campus");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mapa = new MapaCampus();
        inicializarMapa();

        JPanel controles = new JPanel();
        comboOrigen = new JComboBox<>();
        comboDestino = new JComboBox<>();
        chkEvitarEscaleras = new JCheckBox("Evitar escaleras");
        JButton btnCalcular = new JButton("Calcular Ruta");

        for (Edificio e : mapa.obtenerTodosLosEdificios()) {
            comboOrigen.addItem(e);
            comboDestino.addItem(e);
        }

        controles.add(new JLabel("Origen:"));
        controles.add(comboOrigen);
        controles.add(new JLabel("Destino:"));
        controles.add(comboDestino);
        controles.add(chkEvitarEscaleras);
        controles.add(btnCalcular);

        resultadoArea = new JTextArea(10, 50);
        resultadoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultadoArea);

        JPanel superior = new JPanel(new BorderLayout());
        superior.add(controles, BorderLayout.NORTH);
        superior.add(scroll, BorderLayout.CENTER);

        PanelMapaVisual panelVisual = new PanelMapaVisual(mapa);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, superior, panelVisual);
        splitPane.setDividerLocation(350);
        splitPane.setResizeWeight(0.6);

        add(splitPane);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularRuta();
                panelVisual.repaint();
            }
        });
    }

    private void inicializarMapa() {
        Edificio a = new Edificio("A", 0);
        Edificio b = new Edificio("B", 1);
        Edificio c = new Edificio("C", 2);
        Edificio d = new Edificio("D", 3);
        Edificio e = new Edificio("E", 4);
        Edificio f = new Edificio("F", 5);
        Edificio g = new Edificio("G", 6);
        Edificio h = new Edificio("H", 7);
        Edificio i = new Edificio("I", 8);
        Edificio j = new Edificio("J", 9);
        Edificio k = new Edificio("K", 10);
        Edificio l = new Edificio("L", 11);
        Edificio p1 = new Edificio("P1", 12);
        Edificio cf = new Edificio("CF", 13);

        Edificio[] edificios = {a, b, c, d, e, f, g, h, i, j, k, l, p1, cf};
        for (Edificio ed : edificios) mapa.agregarEdificio(ed);

        mapa.agregarCamino(p1, c, 30, false, true);
        mapa.agregarCamino(p1, j, 30, false, true);
        mapa.agregarCamino(a, b, 10, false, true);
        mapa.agregarCamino(b, cf, 15, true, true);
        mapa.agregarCamino(b, c, 10, false, true);
        mapa.agregarCamino(c, cf, 15, false, true);
        mapa.agregarCamino(c, j, 15, false, true);
        mapa.agregarCamino(cf, d, 30, false, true);
        mapa.agregarCamino(d, g, 25, false, true);
        mapa.agregarCamino(d, j, 40, true, true);
        mapa.agregarCamino(d, k, 250, false, true);
        mapa.agregarCamino(d, e, 40, false, true);
        mapa.agregarCamino(e, f, 10, false, true);
        mapa.agregarCamino(e, i, 60, false, true);
        mapa.agregarCamino(f, g, 20, false, true);
        mapa.agregarCamino(f, h, 15, false, true);
        mapa.agregarCamino(h, i, 80, false, true);
        mapa.agregarCamino(h, l, 110, true, true);
        mapa.agregarCamino(i, k, 100, false, true);
        mapa.agregarCamino(i, l, 10, false, true);
        mapa.agregarCamino(k, l, 15, false, true);
    }

    private void calcularRuta() {
        Edificio origen = (Edificio) comboOrigen.getSelectedItem();
        Edificio destino = (Edificio) comboDestino.getSelectedItem();
        boolean evitarEscaleras = chkEvitarEscaleras.isSelected();

        resultadoArea.setText("");
        mapa.buscarRutaMasCorta(origen, destino, evitarEscaleras);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MapaCampusSwing().setVisible(true);
        });
    }
}





