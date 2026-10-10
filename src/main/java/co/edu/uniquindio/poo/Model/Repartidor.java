package co.edu.uniquindio.poo.Model;

public class Repartidor {

    private final String identificacion;
    private final String nombre;
    private final String zona;
    private boolean disponible;

    public Repartidor(String identificacion, String nombre, String zona, boolean disponible) {
        if (identificacion == null || identificacion.isBlank())
            throw new IllegalArgumentException("Identificacion invalida");
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre invalido");
        if (zona == null || zona.isBlank())
            throw new IllegalArgumentException("Zona invalida");
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.zona = zona;
        this.disponible = disponible;
    }

    public String getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public String getZona() { return zona; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repartidor)) return false;
        return identificacion.equals(((Repartidor) o).identificacion);
    }

    @Override public int hashCode() { return identificacion.hashCode(); }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) %s",
                identificacion, nombre, zona, disponible ? "DISPONIBLE" : "NO DISPONIBLE");
    }

}