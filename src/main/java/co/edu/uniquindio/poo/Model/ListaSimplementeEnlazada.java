package co.edu.uniquindio.poo.Model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import co.edu.uniquindio.poo.service.ColeccionPropia;

public class ListaSimplementeEnlazada<T> implements ColeccionPropia<T> {

    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;
        Nodo(T valor) { this.valor = valor; }
    }

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamano;

    @Override
    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        tamano++;
    }

    @Override
    public boolean eliminar(T elemento) {
        if (primero == null) return false;

        // Caso: eliminar el primero
        if (primero.valor.equals(elemento)) {
            primero = primero.siguiente;
            if (primero == null) ultimo = null;
            tamano--;
            return true;
        }

        // Caso: eliminar intermedio o último
        Nodo<T> actual = primero;
        while (actual.siguiente != null) {
            if (actual.siguiente.valor.equals(elemento)) {
                actual.siguiente = actual.siguiente.siguiente;
                if (actual.siguiente == null) ultimo = actual;
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public T obtener(int posicion) {
        if (posicion < 0 || posicion >= tamano)
            throw new IndexOutOfBoundsException("Posicion invalida: " + posicion);
        Nodo<T> actual = primero;
        for (int i = 0; i < posicion; i++) actual = actual.siguiente;
        return actual.valor;
    }

    @Override public int tamano() { return tamano; }
    @Override public boolean estaVacia() { return primero == null; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = primero;

            @Override public boolean hasNext() { return actual != null; }

            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T v = actual.valor;
                actual = actual.siguiente;
                return v;
            }
        };
    }
}