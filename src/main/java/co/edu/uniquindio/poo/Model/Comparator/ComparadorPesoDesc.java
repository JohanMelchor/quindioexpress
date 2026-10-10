package co.edu.uniquindio.poo.Model.Comparator;

import java.util.Comparator;
import co.edu.uniquindio.poo.Model.Paquete;

public class ComparadorPesoDesc implements Comparator<Paquete> {

    @Override
    public int compare(Paquete a, Paquete b) {
        return Double.compare(b.getPeso(), a.getPeso());
    }

}
