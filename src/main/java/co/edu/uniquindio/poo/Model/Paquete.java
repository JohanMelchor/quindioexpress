package co.edu.uniquindio.poo.Model;
public class Paquete implements Comparable<Paquete> {

    private final String codigo;
    private final String destino;
    private final double peso;
    private final int prioridad;
    private final int tiempoEstimado;

    public Paquete(String codigo, String destino, double peso, int prioridad, int tiempoEstimado) {
        if (codigo == null || codigo.isBlank())
            throw new IllegalArgumentException("Codigo invalido");
        if (destino == null || destino.isBlank())
            throw new IllegalArgumentException("Destino invalido");
        if (peso <= 0)
            throw new IllegalArgumentException("Peso debe ser > 0");
        if (prioridad < 1 || prioridad > 5)
            throw new IllegalArgumentException("Prioridad entre 1 y 5");
        if (tiempoEstimado <= 0)
            throw new IllegalArgumentException("Tiempo debe ser > 0");

        this.codigo = codigo;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getCodigo() { return codigo; }
    public String getDestino() { return destino; }
    public double getPeso() { return peso; }
    public int getPrioridad() { return prioridad; }
    public int getTiempoEstimado() { return tiempoEstimado; }

    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paquete)) return false;
        return codigo.equals(((Paquete) o).codigo);
    }

    @Override public int hashCode() { return codigo.hashCode(); }

    @Override
    public String toString() {
        return String.format("%s [dest=%s, peso=%.2f, prio=%d, t=%d]",
                codigo, destino, peso, prioridad, tiempoEstimado);
    }
}