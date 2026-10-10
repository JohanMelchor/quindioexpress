package co.edu.uniquindio.poo.service;

public interface ColeccionPropia<T> extends Iterable<T> {
    void agregar(T elemento);
    boolean eliminar(T elemento);
    T obtener(int posicion);
    int tamano();
    boolean estaVacia();
}
