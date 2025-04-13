package co.edu.upb.Proyecto_Aula2025;

public class Principal {

	public static void main(String[] args) {
		MapaCampus mapa = new MapaCampus();

        Edificio p1 = new Edificio("P1", 0);
        Edificio a = new Edificio("A", 1);
        Edificio b = new Edificio("B", 2);
        Edificio c = new Edificio("C", 3);
        Edificio cf = new Edificio("CF", 4);
        Edificio d = new Edificio("D", 5);
        Edificio e = new Edificio("E", 6);
        Edificio f = new Edificio("F", 7);
        Edificio g = new Edificio("G", 8);
        Edificio h = new Edificio("H", 9);
        Edificio i = new Edificio("I", 10);
        Edificio j = new Edificio("J", 11);
        Edificio k = new Edificio("K", 12);
        Edificio l = new Edificio("L", 13);

        // Agregar edificios al mapa
        mapa.agregarEdificio(p1);
        mapa.agregarEdificio(a);
        mapa.agregarEdificio(b);
        mapa.agregarEdificio(c);
        mapa.agregarEdificio(cf);
        mapa.agregarEdificio(d);
        mapa.agregarEdificio(e);
        mapa.agregarEdificio(f);
        mapa.agregarEdificio(g);
        mapa.agregarEdificio(h);
        mapa.agregarEdificio(i);
        mapa.agregarEdificio(j);
        mapa.agregarEdificio(k);
        mapa.agregarEdificio(l);

        // Agregar caminos
        mapa.agregarCamino(p1, c, 30, false, true);
        mapa.agregarCamino(p1, j, 30, false, true);
        mapa.agregarCamino(a, b, 10, false, true);
        mapa.agregarCamino(b, cf, 15, true, true);
        mapa.agregarCamino(b, c, 10, false, true);
        mapa.agregarCamino(c, cf, 15, true, true);
        mapa.agregarCamino(c, j, 15, false, true);
        mapa.agregarCamino(cf, d, 30, true, true);
        mapa.agregarCamino(d, g, 25, false, true);
        mapa.agregarCamino(d, j, 40, false, true);
        mapa.agregarCamino(d, k, 250, true, true);
        mapa.agregarCamino(d, e, 40, true, true);
        mapa.agregarCamino(e, f, 10, false, true);
        mapa.agregarCamino(e, i, 60, true, true);
        mapa.agregarCamino(f, g, 20, false, true);
        mapa.agregarCamino(f, h, 15, false, true);
        mapa.agregarCamino(h, i, 80, true, true);
        mapa.agregarCamino(h, l, 110, false, true);
        mapa.agregarCamino(i, k, 100, true, true);
        mapa.agregarCamino(i, l, 10, false, true);
        mapa.agregarCamino(k, l, 15, false, true);

        // Prueba de ruta más corta
        mapa.buscarRutaMasCorta(p1, l, true); 
    }

}
