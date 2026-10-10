package co.edu.uniquindio.poo.Model.Comparator;

import co.edu.uniquindio.poo.Model.Paquete;
import java.util.Comparator;

public class ComparadorTiempoAsc implements Comparator<Paquete> {
    @Override
    public int compare(Paquete a, Paquete b) {
        return Integer.compare(a.getTiempoEstimado(), b.getTiempoEstimado());
    }
}
