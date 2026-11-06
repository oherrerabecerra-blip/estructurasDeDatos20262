    
package ProyectoFinal;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
public class EjecutarUni {
    static Scanner sc = new Scanner(System.in);
    static aula aula1 = new aula("Aula 101", 30);
    static HorarioAulas horario;
    static String[] edificios = {"Biblioteca","Bloque A","Bloque B","Laboratorios"};
    static int[][] matriz = new int[5][5];
    static DistanciaEdi grafo = new DistanciaEdi(edificios, matriz);
    static Dijkstra dijkstra = new Dijkstra(grafo);
    static Notas notas = new Notas();
    static Notas.deshacerRehacer historial = new Notas.deshacerRehacer();
    static Set<String> estudiantes = new HashSet<>();
    public static void main(String[] args) {
        // Conexiones entre edificios
        grafo.conectarEdificio("Biblioteca", "Bloque A", 100);
        grafo.conectarEdificio("Biblioteca", "Bloque B", 150);
        grafo.conectarEdificio("Bloque A", "Bloque B", 50);
        grafo.conectarEdificio("Bloque B", "Laboratorios", 80);
        grafo.conectarEdificio("biblioteca", "Laboratorios", 90);
        int opcion;
        do {
            System.out.println("\n================================");
            System.out.println("       SISTEMA UNIVERSITARIO");
            System.out.println("================================");
            System.out.println("1. Gestionar aulas");
            System.out.println("2. Gestionar horarios");
            System.out.println("3. Mostrar distancias entre edificios");
            System.out.println("4. Buscar ruta con Dijkstra");
            System.out.println("5. Gestionar notas");
            System.out.println("0. Salir");
            System.out.println("================================");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    menuAulas();
                    break;
                case 2:
                    menuHorarios();
                    break;
                case 3:
                    grafo.mostrarDistancia();
                    break;
                case 4:
                    menuDijkstra();
                    break;
                case 5:
                    menuNotas();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
        sc.close();
    }
    // menu de gestion de aulas
    public static void menuAulas() {
        int opcion;
        do {
            System.out.println("\n----- GESTION DE AULAS -----");
            System.out.println("1. Mostrar informacion");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Reservar aula");
            System.out.println("4. Liberar aula");
            System.out.println("0. Volver");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    aula1.mostrarInformacion();
                    break;
                case 2:
                    System.out.print("Dia (0-6): ");
                    int dia = leerEntero();
                    System.out.print("Hora (0-23): ");
                    int hora = leerEntero();
                    if (dia >= 0 && dia < 7 &&
                        hora >= 0 && hora < 24) {
                        if (aula1.consultarDisponibilidad(dia, hora)) {
                            System.out.println("El aula esta disponible.");
                        } else {
                            System.out.println("El aula esta ocupada.");
                        }
                    } else {
                        System.out.println("Dia u hora invalida.");
                    }
                    break;
                case 3:
                    System.out.print("Dia (0-6): ");
                    dia = leerEntero();
                    System.out.print("Hora de inicio (0-23): ");
                    hora = leerEntero();
                    System.out.print("Duracion en horas: ");
                    int duracion = leerEntero();
                    if (dia >= 0 && dia < 7 &&
                        hora >= 0 && hora < 24 &&
                        duracion > 0 && hora + duracion <= 24) {
                        if (aula1.reservar(dia, hora, duracion)) {
                            System.out.println("Reserva realizada correctamente.");
                        } else {
                            System.out.println("El aula ya esta ocupada.");
                        }
                    } else {
                        System.out.println("Datos de reserva invalidos.");
                    }
                    break;
                case 4:
                    System.out.print("Dia (0-6): ");
                    dia = leerEntero();
                    System.out.print("Hora de inicio (0-23): ");
                    hora = leerEntero();
                    System.out.print("Duracion en horas: ");
                    duracion = leerEntero();
                    if (dia >= 0 && dia < 7 &&
                        hora >= 0 && hora < 24 &&
                        duracion > 0 && hora + duracion <= 24) {
                        aula1.liberar(dia, hora, duracion);
                        System.out.println("Horario liberado.");
                    } else {
                        System.out.println("Datos invalidos.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }
    // menu para gestion de horarios
    public static void menuHorarios() {
        int opcion;
        do {
            System.out.println("\n----- GESTION DE HORARIOS -----");
            System.out.println("1. Registrar horario");
            System.out.println("2. Mostrar horario");
            System.out.println("3. Reservar horario");
            System.out.println("4. Liberar horario");
            System.out.println("0. Volver");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    System.out.print("Codigo del aula: ");
                    String codigo = sc.nextLine();
                    System.out.print("Nombre del aula: ");
                    String nombre = sc.nextLine();
                    System.out.print("Dia: ");
                    String dia = sc.nextLine();
                    System.out.print("Hora de inicio: ");
                    String inicio = sc.nextLine();
                    System.out.print("Hora de fin: ");
                    String fin = sc.nextLine();
                    System.out.print("Nombre de la materia: ");
                    String materia = sc.nextLine();
                    System.out.print("Nombre del profesor: ");
                    String profesor = sc.nextLine();
                    horario = new HorarioAulas(
                        codigo, nombre, dia,
                        inicio, fin, materia,
                        profesor, true
                    );
                    System.out.println("Horario registrado.");
                    break;
                case 2:
                    if (horario != null) {
                        horario.mostrarInformacion();
                    } else {
                        System.out.println("No hay horarios registrados.");
                    }
                    break;
                case 3:
                    if (horario != null) {
                        horario.reserva();
                    } else {
                        System.out.println("Primero registre un horario.");
                    }
                    break;
                case 4:
                    if (horario != null) {
                        horario.liberar();
                    } else {
                        System.out.println("No hay horarios registrados.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }
    // menu para gestion de rutas con dijkstra
    public static void menuDijkstra() {
        System.out.println("\n----- RUTAS UNIVERSITARIAS -----");
        System.out.println("Edificios disponibles:");
        for (String edificio : edificios) {
            System.out.println("- " + edificio);
        }
        System.out.print("Edificio de origen: ");
        String origen = sc.nextLine();
        System.out.print("Edificio de destino: ");
        String destino = sc.nextLine();
        dijkstra.calcularRuta(origen, destino);
    }
    // menu para gestion de notas
    public static void menuNotas() {
        int opcion;
        do {
            System.out.println("\n----- GESTION DE NOTAS -----");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar nota");
            System.out.println("3. Mostrar notas");
            System.out.println("4. Consultar promedio semestral");
            System.out.println("5. Consultar materias reprobadas");
            System.out.println("6. Deshacer cambio");
            System.out.println("7. Rehacer cambio");
            System.out.println("0. Volver");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    System.out.print("ID del estudiante: ");
                    String id = sc.nextLine();
                    if (!estudiantes.contains(id)) {
                        notas.agregarEstudiante(id);
                        estudiantes.add(id);
                    } else {
                        System.out.println("El estudiante ya esta registrado.");
                    }
                    break;
                case 2:
                    System.out.print("ID del estudiante: ");
                    id = sc.nextLine();
                    if (!estudiantes.contains(id)) {
                        System.out.println("Estudiante no registrado.");
                        break;
                    }
                    System.out.print("Semestre (1-10): ");
                    int semestre = leerEntero();
                    System.out.print("Nombre de la materia: ");
                    String materia = sc.nextLine();
                    System.out.print("Nota (0-5): ");
                    double nota = leerDouble();
                    notas.registarNotas(
                        id, semestre, materia,
                        nota, historial, 0.0
                    );
                    break;
                case 3:
                    System.out.print("ID del estudiante: ");
                    id = sc.nextLine();
                    if (estudiantes.contains(id)) {
                        notas.mostrarNotas(id);
                    } else {
                        System.out.println("Estudiante no registrado.");
                    }
                    break;
                case 4:
                    System.out.print("ID del estudiante: ");
                    id = sc.nextLine();
                    if (!estudiantes.contains(id)) {
                        System.out.println("Estudiante no registrado.");
                        break;
                    }
                    System.out.print("Semestre: ");
                    semestre = leerEntero();
                    Double promedio = notas.promedioNotas(id, semestre);
                    if (promedio != null) {
                        System.out.println("Promedio: " + promedio);
                    }
                    break;
                case 5:
                    System.out.print("ID del estudiante: ");
                    id = sc.nextLine();
                    if (estudiantes.contains(id)) {
                        notas.materiasReprobadas(id);
                    } else {
                        System.out.println("Estudiante no registrado.");
                    }
                    break;
                case 6:
                    historial.deshacer();
                    System.out.println("Operacion de deshacer ejecutada.");
                    break;
                case 7:
                    historial.rehacer();
                    System.out.println("Operacion de rehacer ejecutada.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }
    // METODOS PARA LEER DATOS
    public static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }
    }
    public static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}