package co.edu.uniquindio.poo.Model.Comparator;

import java.util.Comparator;
import co.edu.uniquindio.poo.Model.Paquete;

public class ComparadorDespacho implements Comparator<Paquete> {
    @Override
    public int compare(Paquete a, Paquete b) {
        int c = Integer.compare(b.getPrioridad(), a.getPrioridad());
        if (c != 0) return c;
        c = Integer.compare(a.getTiempoEstimado(), b.getTiempoEstimado());
        if (c != 0) return c;
        return a.getCodigo().compareTo(b.getCodigo());
    }
}
