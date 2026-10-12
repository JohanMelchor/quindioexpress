package co.edu.uniquindio.poo.Model;

import co.edu.uniquindio.poo.Model.Comparator.ComparadorTiempoAsc;
import co.edu.uniquindio.poo.Model.Comparator.ComparadorPrioridadDesc;
import co.edu.uniquindio.poo.Model.Comparator.ComparadorDespacho;
import co.edu.uniquindio.poo.Model.Comparator.ComparadorPesoDesc;
import java.util.*;

public class GestorQuindioExpress {

    // Colecciones principales
    private final List<Paquete> paquetesRegistrados = new ArrayList<>();       
    private final Map<String, Paquete> paquetesPorCodigo = new HashMap<>();    
    private final Set<String> municipios = new HashSet<>();                    
    private final Set<String> municipiosOrdenados = new TreeSet<>();           
    private final Map<String, List<Paquete>> paquetesPorDestino = new HashMap<>();

    // Colas para atencion
    private final Queue<Paquete> colaLlegada = new ArrayDeque<>();             // FIFO
    private final PriorityQueue<Paquete> colaPrioridad =
            new PriorityQueue<>(new ComparadorDespacho());                     // despacho prioritario

    // Historial con lista propia
    private final ListaSimplementeEnlazada<Paquete> historialEntregas =
            new ListaSimplementeEnlazada<>();

    // Repartidores
    private final Map<String, Repartidor> repartidores = new HashMap<>();

    // Marcador de entregados (para descartar de colas)
    private final Set<String> entregados = new HashSet<>();


    // REGISTRO
    public boolean registrarPaquete(Paquete p) {
        if (p == null) return false;
        if (paquetesPorCodigo.containsKey(p.getCodigo())) {
            System.out.println("[RECHAZADO] Codigo duplicado: " + p.getCodigo());
            return false;
        }
        paquetesRegistrados.add(p);
        paquetesPorCodigo.put(p.getCodigo(), p);
        municipios.add(p.getDestino());
        municipiosOrdenados.add(p.getDestino());
        paquetesPorDestino
                .computeIfAbsent(p.getDestino(), k -> new ArrayList<>())
                .add(p);
        colaLlegada.offer(p);
        colaPrioridad.offer(p);
        return true;
    }

    public boolean registrarRepartidor(Repartidor r) {
        if (r == null) return false;
        if (repartidores.containsKey(r.getIdentificacion())) return false;
        repartidores.put(r.getIdentificacion(), r);
        return true;
    }


    //  CONSULTAS
    /** Consulta O(1) promedio. */
    public Paquete consultarPorCodigo(String codigo) {
        return paquetesPorCodigo.get(codigo);
    }

    public Set<String> getMunicipios() {
        return new HashSet<>(municipios);
    }

    public List<String> getMunicipiosOrdenados() {
        return new ArrayList<>(municipiosOrdenados);
    }

    public List<Paquete> paquetesDeDestino(String destino) {
        return paquetesPorDestino.getOrDefault(destino, Collections.emptyList());
    }


    
    // FIFO(ignora ya entregados)
    public Paquete atenderPorOrdenDeLlegada() {
        while (!colaLlegada.isEmpty()) {
            Paquete p = colaLlegada.peek();
            if (entregados.contains(p.getCodigo())) {
                colaLlegada.poll();
            } else {
                return p;
            }
        }
        return null;
    }

    // Devuelve el siguiente paquete segun las reglas de prioridad
    public Paquete siguienteDespachoPrioritario() {
        while (!colaPrioridad.isEmpty()) {
            Paquete p = colaPrioridad.peek();
            if (entregados.contains(p.getCodigo())) {
                colaPrioridad.poll();
            } else {
                return p;
            }
        }
        return null;
    }

    // Registra entrega: sale de pendientes y entra al historial
    public boolean registrarEntrega(Paquete p) {
        if (p == null) return false;
        if (!paquetesPorCodigo.containsKey(p.getCodigo())) return false;
        if (entregados.contains(p.getCodigo())) return false;
        entregados.add(p.getCodigo());
        historialEntregas.agregar(p);
        return true;
    }

    public ListaSimplementeEnlazada<Paquete> getHistorialEntregas() {
        return historialEntregas;
    }


    //  ORDENAMIENTOS
    public List<Paquete> ordenadosNatural() {
        List<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia);
        return copia;
    }

    public List<Paquete> ordenadosPorPrioridad() {
        List<Paquete> c = new ArrayList<>(paquetesRegistrados);
        c.sort(new ComparadorPrioridadDesc());
        return c;
    }

    public List<Paquete> ordenadosPorPeso() {
        List<Paquete> c = new ArrayList<>(paquetesRegistrados);
        c.sort(new ComparadorPesoDesc());
        return c;
    }

    public List<Paquete> ordenadosPorTiempo() {
        List<Paquete> c = new ArrayList<>(paquetesRegistrados);
        c.sort(new ComparadorTiempoAsc());
        return c;
    }


    //  RECURSIVIDAD
    // Peso total de paquetes hacia un destino
    public double pesoTotalPorDestino(String destino) {
        return pesoTotalRec(0, destino);
    }

    private double pesoTotalRec(int i, String destino) {
        if (i == paquetesRegistrados.size()) return 0.0;
        Paquete p = paquetesRegistrados.get(i);
        double suma = p.getDestino().equals(destino) ? p.getPeso() : 0.0;
        return suma + pesoTotalRec(i + 1, destino);
    }
    public int contarPrioridadMayorIgual(int minima) {
        return contarPrioridadRec(0, minima);
    }

    private int contarPrioridadRec(int i, int minima) {
        if (i == paquetesRegistrados.size()) return 0;
        int aporte = paquetesRegistrados.get(i).getPrioridad() >= minima ? 1 : 0;
        return aporte + contarPrioridadRec(i + 1, minima);
    }

    //  DIVIDE Y VENCERAS

    public Paquete paqueteMayorPeso() {
        if (paquetesRegistrados.isEmpty()) return null;
        return mayorPesoRec(0, paquetesRegistrados.size() - 1);
    }

    private Paquete mayorPesoRec(int ini, int fin) {
        
        if (ini == fin) return paquetesRegistrados.get(ini);
        
        int mid = (ini + fin) / 2;
        
        Paquete izq = mayorPesoRec(ini, mid);
        Paquete der = mayorPesoRec(mid + 1, fin);
        
        return izq.getPeso() >= der.getPeso() ? izq : der;
    }

    //  BUSQUEDA BINARIA
    public Paquete busquedaBinariaPorCodigo(String codigo) {
        List<Paquete> orden = ordenadosNatural();
        int ini = 0, fin = orden.size() - 1;
        while (ini <= fin) {
            int mid = (ini + fin) / 2;
            int cmp = orden.get(mid).getCodigo().compareTo(codigo);
            if (cmp == 0) return orden.get(mid);
            if (cmp < 0) ini = mid + 1;
            else fin = mid - 1;
        }
        return null;
    }

    //  ASIGNACION DE REPARTIDORES
    
    /**
     * Asigna un repartidor disponible cuya zona coincida con el destino.
     * Reglas: no disponible no recibe; zona debe coincidir.
     */
    public Repartidor asignarRepartidor(Paquete p) {
        if (p == null) return null;
        if (entregados.contains(p.getCodigo())) return null;
        for (Repartidor r : repartidores.values()) {
            if (r.isDisponible() && r.getZona().equals(p.getDestino())) {
                r.setDisponible(false);
                return r;
            }
        }
        return null;
    }

    public Collection<Repartidor> getRepartidores() {
        return repartidores.values();
    }
}