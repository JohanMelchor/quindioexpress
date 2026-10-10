package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.Model.GestorQuindioExpress;
import co.edu.uniquindio.poo.Model.Paquete;
import co.edu.uniquindio.poo.Model.Repartidor;
import co.edu.uniquindio.poo.Model.ListaSimplementeEnlazada;

public class Main {
    public static void main(String[] args) {

        GestorQuindioExpress gestor = new GestorQuindioExpress();

        cargarRepartidores(gestor);
        cargarPaquetes(gestor);

        titulo("1. PAQUETES REGISTRADOS (orden natural por codigo)");
        demoRegistroYConsultas(gestor);

        titulo("2. MUNICIPIOS Y AGRUPACION POR DESTINO");
        demoMunicipios(gestor);

        titulo("3. ORDENAMIENTOS CON COMPARATORS");
        demoOrdenamientos(gestor);

        titulo("4. ATENCION DE PAQUETES (FIFO y PRIORIDAD)");
        demoColas(gestor);

        titulo("5. OPERACIONES RECURSIVAS");
        demoRecursivos(gestor);

        titulo("6. DIVIDE Y VENCERAS");
        demoDivideYVenceras(gestor);

        titulo("7. BUSQUEDA BINARIA");
        demoBusquedaBinaria(gestor);

        titulo("8. ASIGNACION DE REPARTIDORES");
        demoAsignacion(gestor);

        titulo("9. HISTORIAL DE ENTREGAS (lista propia)");
        demoHistorial(gestor);

        titulo("FIN DE LA DEMOSTRACION");
    }

    
    //  QUEMA DE DATOS

    private static void cargarRepartidores(GestorQuindioExpress g) {
        // 6 repartidores, cada uno con su zona principal
        g.registrarRepartidor(new Repartidor("R001", "Juan Perez",   "Armenia",    true));
        g.registrarRepartidor(new Repartidor("R002", "Maria Lopez",  "Salento",    true));
        g.registrarRepartidor(new Repartidor("R003", "Carlos Ruiz",  "Calarca",    true));
        g.registrarRepartidor(new Repartidor("R004", "Ana Gomez",    "Montenegro", true));
        g.registrarRepartidor(new Repartidor("R005", "Pedro Diaz",   "Quimbaya",   true));
        g.registrarRepartidor(new Repartidor("R006", "Luisa Torres", "Armenia",    true));
    }

    private static void cargarPaquetes(GestorQuindioExpress g) {
        
        // Armenia
        g.registrarPaquete(new Paquete("PQ731", "Armenia",    3.2,  5, 45));
        g.registrarPaquete(new Paquete("PQ942", "Armenia",    4.0,  4, 20));
        g.registrarPaquete(new Paquete("PQ829", "Armenia",    6.7,  2, 90));
        g.registrarPaquete(new Paquete("PQ903", "Armenia",    7.2,  5, 35));
        g.registrarPaquete(new Paquete("PQ456", "Armenia",    2.3,  5, 15));
        g.registrarPaquete(new Paquete("PQ990", "Armenia",   18.7,  5, 20));
        g.registrarPaquete(new Paquete("PQ245", "Armenia",    2.0,  5,  5));
        g.registrarPaquete(new Paquete("PQ598", "Armenia",    4.4,  5, 40));

        // Salento
        g.registrarPaquete(new Paquete("PQ105", "Salento",    1.5,  5, 25));
        g.registrarPaquete(new Paquete("PQ567", "Salento",    5.1,  5, 30));
        g.registrarPaquete(new Paquete("PQ777", "Salento",    0.9,  1, 120));
        g.registrarPaquete(new Paquete("PQ502", "Salento",    5.1,  3, 65));
        g.registrarPaquete(new Paquete("PQ672", "Salento",    2.6,  4, 35));

        // Calarca
        g.registrarPaquete(new Paquete("PQ318", "Calarca",    2.3,  3, 60));
        g.registrarPaquete(new Paquete("PQ555", "Calarca",    8.4,  4, 50));
        g.registrarPaquete(new Paquete("PQ333", "Calarca",   12.0,  5, 55));
        g.registrarPaquete(new Paquete("PQ639", "Calarca",    1.6,  5, 10));
        g.registrarPaquete(new Paquete("PQ336", "Calarca",    8.1,  5, 45));

        // Montenegro
        g.registrarPaquete(new Paquete("PQ420", "Montenegro", 3.3,  3, 30));
        g.registrarPaquete(new Paquete("PQ188", "Montenegro", 5.5,  3, 45));
        g.registrarPaquete(new Paquete("PQ850", "Montenegro", 9.9,  4, 75));

        // Quimbaya
        g.registrarPaquete(new Paquete("PQ214", "Quimbaya",   1.1,  5, 25));
        g.registrarPaquete(new Paquete("PQ801", "Quimbaya",   3.2,  2, 80));
        g.registrarPaquete(new Paquete("PQ408", "Quimbaya",   3.7,  5, 30));

        // Filandia
        g.registrarPaquete(new Paquete("PQ102", "Filandia",   2.8,  3, 70));
        g.registrarPaquete(new Paquete("PQ266", "Filandia",   4.5,  4, 30));
        g.registrarPaquete(new Paquete("PQ713", "Filandia",   6.3,  2, 100));

        // Circasia
        g.registrarPaquete(new Paquete("PQ611", "Circasia",   4.5,  4, 40));
        g.registrarPaquete(new Paquete("PQ111", "Circasia",   7.4,  5, 25));
        g.registrarPaquete(new Paquete("PQ744", "Circasia",   5.0,  3, 55));

        // Intento de duplicado (debe ser rechazado)
        g.registrarPaquete(new Paquete("PQ731", "Armenia", 1.0, 5, 10));
    }

    //  DEMOSTRACIONES

    private static void demoRegistroYConsultas(GestorQuindioExpress g) {
        subtitulo("Listado completo (orden natural)");
        g.ordenadosNatural().forEach(System.out::println);

        subtitulo("Consulta O(1) por codigo");
        mostrar("PQ731", g.consultarPorCodigo("PQ731"));
        mostrar("PQ999", g.consultarPorCodigo("PQ999"));
    }

    private static void demoMunicipios(GestorQuindioExpress g) {
        subtitulo("Municipios unicos (HashSet)");
        System.out.println(g.getMunicipios());

        subtitulo("Municipios ordenados alfabeticamente (TreeSet)");
        System.out.println(g.getMunicipiosOrdenados());

        subtitulo("Cantidad de paquetes por destino");
        for (String m : g.getMunicipiosOrdenados()) {
            System.out.printf("  %-12s = %d paquetes%n",
                    m, g.paquetesDeDestino(m).size());
        }
    }

    private static void demoOrdenamientos(GestorQuindioExpress g) {
        subtitulo("Por codigo ascendente (orden natural / Comparable)");
        g.ordenadosNatural().stream().limit(5).forEach(System.out::println);

        subtitulo("Por peso descendente");
        g.ordenadosPorPeso().stream().limit(5).forEach(System.out::println);

        subtitulo("Por tiempo estimado ascendente");
        g.ordenadosPorTiempo().stream().limit(5).forEach(System.out::println);

        subtitulo("Por prioridad descendente");
        g.ordenadosPorPrioridad().stream().limit(5).forEach(System.out::println);
    }

    private static void demoColas(GestorQuindioExpress g) {
        subtitulo("Atencion por orden de llegada (FIFO)");
        for (int i = 1; i <= 3; i++) {
            System.out.println("  " + i + "° = " + g.atenderPorOrdenDeLlegada());
        }

        subtitulo("Despacho prioritario (prioridad > tiempo > codigo)");
        for (int i = 1; i <= 5; i++) {
            Paquete p = g.siguienteDespachoPrioritario();
            System.out.println("  " + i + "° = " + p);
            if (p != null) g.registrarEntrega(p);
        }
    }

    private static void demoRecursivos(GestorQuindioExpress g) {
        subtitulo("Peso total por destino");
        System.out.printf("  Armenia = %.2f kg%n", g.pesoTotalPorDestino("Armenia"));
        System.out.printf("  Salento = %.2f kg%n", g.pesoTotalPorDestino("Salento"));

        subtitulo("Conteo de paquetes con prioridad >= X");
        System.out.println("  Prioridad >= 4 = " + g.contarPrioridadMayorIgual(4));
        System.out.println("  Prioridad >= 5 = " + g.contarPrioridadMayorIgual(5));
    }

    private static void demoDivideYVenceras(GestorQuindioExpress g) {
        subtitulo("Paquete de mayor peso");
        System.out.println(" - " + g.paqueteMayorPeso());
    }

    private static void demoBusquedaBinaria(GestorQuindioExpress g) {
        subtitulo("Busqueda binaria por codigo (requiere lista ordenada)");
        mostrar("PQ731", g.busquedaBinariaPorCodigo("PQ731"));
        mostrar("PQ555", g.busquedaBinariaPorCodigo("PQ555"));
        mostrar("PQ000", g.busquedaBinariaPorCodigo("PQ000"));
    }

    private static void demoAsignacion(GestorQuindioExpress g) {
        subtitulo("Asignacion de repartidores (zona debe coincidir)");
        for (Paquete p : g.ordenadosNatural()) {
            Repartidor r = g.asignarRepartidor(p);
            if (r != null) {
                System.out.printf("  %s (%s) = %s%n",
                        p.getCodigo(), p.getDestino(), r.getNombre());
            }
        }

        subtitulo("Estado final de repartidores");
        g.getRepartidores().forEach(r -> System.out.println(" - " + r));
    }

    private static void demoHistorial(GestorQuindioExpress g) {
        subtitulo("Historial de entregas (orden real)");
        for (Paquete p : g.getHistorialEntregas()) {
            System.out.println("  - " + p.getCodigo());
        }
        System.out.println("  Tamaño historial: " + g.getHistorialEntregas().tamano());

        subtitulo("Prueba de la lista propia en distintos escenarios");
        ListaSimplementeEnlazada<String> prueba = new ListaSimplementeEnlazada<>();

        System.out.println("  está vacia? " + prueba.estaVacia());

        prueba.agregar("A");
        System.out.println("  si tiene 1 elemento, cual es el tamaño = " + prueba.tamano());

        prueba.eliminar("A");
        System.out.println("  tras eliminar el unico, está vacia? " + prueba.estaVacia());

        prueba.agregar("X");
        prueba.agregar("Y");
        prueba.agregar("Z");
        prueba.eliminar("Y"); 
        prueba.eliminar("Z");

        System.out.println("  tras eliminar intermedio y ultimo:");
        for (String s : prueba) System.out.println("    " + s);

        System.out.println("  eliminar inexistente - " + prueba.eliminar("QQ"));
    }

    //  UTILIDADES DE IMPRESION

    private static void titulo(String t) {
        System.out.println();
        System.out.println("============================================================");
        System.out.println("  " + t);
        System.out.println("============================================================");
    }

    private static void subtitulo(String t) {
        System.out.println();
        System.out.println(t);
    }

    private static void mostrar(String etiqueta, Paquete p) {
        System.out.printf("  %s - %s%n", etiqueta, (p == null ? "no encontrado" : p));
    }
}