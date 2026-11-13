package ProyectoFinal;
import java.util.Scanner;
public class EjecutarUni {
    private static final Scanner sc = new Scanner(System.in);
    private static final RegistroEstudiantes estudiantes = new RegistroEstudiantes();
    private static final GestorMaterias materias = new GestorMaterias();
    private static final RegistroAulas aulas = new RegistroAulas();
    private static final RegistroFacultades facultades = new RegistroFacultades();
    private static final Notas notas = new Notas();
    private static final DistanciaEdi rutas = new DistanciaEdi(20);
    private static final ProcesamientoBatch batch = new ProcesamientoBatch();
    private static final NavegacionReportes navegador = new NavegacionReportes();
    public static void main(String[] args) {
        int opcion;
        do {
            menu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                switch (opcion) {
                    case 1 -> registrarEstudiante();
                    case 2 -> buscarEstudiante();
                    case 3 -> listarEstudiantes();
                    case 4 -> crearMateria();
                    case 5 -> inscribirEstudiante();
                    case 6 -> retirarEstudiante();
                    case 7 -> mostrarMaterias();
                    case 8 -> registrarAula();
                    case 9 -> reservarHorario();
                    case 10 -> consultarAula();
                    case 11 -> agregarEdificio();
                    case 12 -> conectarEdificios();
                    case 13 -> calcularRuta();
                    case 14 -> registrarNota();
                    case 15 -> verNotas();
                    case 16 -> cambiarNota();
                    case 17 -> notas.deshacer();
                    case 18 -> notas.rehacer();
                    case 19 -> agregarSolicitudBatch();
                    case 20 -> procesarBatch();
                    case 21 -> agregarFacultad();
                    case 22 -> facultades.listar();
                    case 23 -> navegarReporte();
                    case 0 -> System.out.println("Hasta luego.");
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (RuntimeException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (opcion != 0);
    }
    private static void menu() {
        System.out.println("\\n========== PLANIFICACION ACADEMICA ==========");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Buscar estudiante por ID");
        System.out.println("3. Listar estudiantes");
        System.out.println("4. Crear materia");
        System.out.println("5. Inscribir estudiante");
        System.out.println("6. Cancelar inscripcion");
        System.out.println("7. Listar materias");
        System.out.println("8. Registrar aula");
        System.out.println("9. Reservar horario");
        System.out.println("10. Consultar/listar aulas");
        System.out.println("11. Agregar edificio");
        System.out.println("12. Conectar edificios");
        System.out.println("13. Calcular ruta mas corta (Dijkstra)");
        System.out.println("14. Registrar nota");
        System.out.println("15. Ver reporte de notas");
        System.out.println("16. Cambiar nota");
        System.out.println("17. Deshacer cambio de nota");
        System.out.println("18. Rehacer cambio de nota");
        System.out.println("19. Agregar solicitud batch");
        System.out.println("20. Procesar cola batch");
        System.out.println("21. Registrar facultad");
        System.out.println("22. Listar facultades");
        System.out.println("23. Guardar/consultar navegacion de reportes");
        System.out.println("0. Salir");
    }
    private static void registrarEstudiante() {
        String id = leerTexto("ID: ");
        String nombre = leerTexto("Nombre: ");
        String email = leerTexto("Email: ");
        String programa = leerTexto("Programa: ");
        int semestre = leerEntero("Semestre actual: ");
        estudiantes.registrar(new Estudiante(id, nombre, email, programa, semestre));
        notas.crearRegistro(id);
    }
    private static void buscarEstudiante() {
        Estudiante e = estudiantes.buscarPorId(leerTexto("ID: "));
        if (e == null) System.out.println("Estudiante no encontrado.");
        else e.mostrarInformacion();
    }
    private static void listarEstudiantes() {
        for (Estudiante e : estudiantes.listar()) e.mostrarInformacion();
        if (estudiantes.cantidad() == 0) System.out.println("No hay estudiantes.");
    }
    private static void crearMateria() {
        materias.crearMateria(leerTexto("Codigo: "), leerTexto("Nombre: "), leerEntero("Cupo: "));
    }

    private static void inscribirEstudiante() {
        String id = leerTexto("ID del estudiante: ");
        String codigo = leerTexto("Codigo de materia: ");
        Estudiante e = estudiantes.buscarPorId(id);
        if (e == null) System.out.println("Estudiante no encontrado.");
        else materias.inscribir(codigo, e);
    }

    private static void retirarEstudiante() {
        String id = leerTexto("ID del estudiante: ");
        String codigo = leerTexto("Codigo de materia: ");
        Estudiante e = estudiantes.buscarPorId(id);
        if (e == null) System.out.println("Estudiante no encontrado.");
        else if (materias.retirar(codigo, e)) System.out.println("Inscripcion cancelada.");
        else System.out.println("No se pudo cancelar la inscripcion.");
    }

    private static void mostrarMaterias() {
        for (Materia m : materias.listarMaterias()) System.out.println(m);
        if (materias.listarMaterias().isEmpty()) System.out.println("No hay materias.");
    }

    private static void registrarAula() {
        String nombre = leerTexto("Nombre/codigo del aula: ");
        int capacidad = leerEntero("Capacidad: ");
        aulas.registrar(new Aula(nombre, capacidad));
        System.out.println("Aula registrada.");
    }

    private static void reservarHorario() {
        Aula a = aulas.buscar(leerTexto("Nombre del aula: "));
        if (a == null) { System.out.println("Aula no encontrada."); return; }
        int dia = leerEntero("Dia (0 domingo - 6 sabado): ");
        int hora = leerEntero("Hora inicial (0-23): ");
        int duracion = leerEntero("Duracion en horas: ");
        System.out.println(a.reservar(dia, hora, duracion) ? "Horario reservado." : "No se pudo reservar: horario invalido u ocupado.");
    }

    private static void consultarAula() {
        aulas.listar();
        String nombre = leerTexto("Nombre del aula para consultar (Enter para omitir): ");
        if (!nombre.isBlank()) {
            Aula a = aulas.buscar(nombre);
            if (a == null) System.out.println("Aula no encontrada.");
            else a.mostrarEstado();
        }
    }

    private static void agregarEdificio() {
        rutas.agregarEdificio(leerTexto("Nombre del edificio: "));
        System.out.println("Edificio agregado.");
    }

    private static void conectarEdificios() {
        String a = leerTexto("Edificio A: ");
        String b = leerTexto("Edificio B: ");
        int distancia = leerEntero("Distancia en metros: ");
        rutas.conectar(a, b, distancia);
        System.out.println("Conexion registrada.");
    }

    private static void calcularRuta() {
        rutas.mostrarMatriz();
        rutas.dijkstra(leerTexto("Origen: "), leerTexto("Destino: "));
    }

    private static void registrarNota() {
        String id = leerTexto("ID estudiante: ");
        if (estudiantes.buscarPorId(id) == null) { System.out.println("Estudiante no encontrado."); return; }
        int semestre = leerEntero("Semestre (1-10): ") - 1;
        int posicion = leerEntero("Posicion de materia (1-20): ") - 1;
        String materia = leerTexto("Nombre de materia: ");
        double nota = leerDouble("Nota (0-5): ");
        notas.agregarNota(id, semestre, posicion, materia, nota);
        System.out.println("Nota registrada.");
    }

    private static void verNotas() {
        String id = leerTexto("ID estudiante: ");
        navegador.visitar("Reporte de notas del estudiante " + id);
        notas.mostrar(id);
    }

    private static void cambiarNota() {
        String id = leerTexto("ID estudiante: ");
        int semestre = leerEntero("Semestre (1-10): ") - 1;
        int posicion = leerEntero("Posicion (1-20): ") - 1;
        double nueva = leerDouble("Nueva nota: ");
        notas.cambiarNota(id, semestre, posicion, nueva);
    }

    private static void agregarSolicitudBatch() {
        batch.agregarSolicitud(new SolicitudInscripcion(
                leerTexto("ID estudiante: "), leerTexto("Codigo materia: ")));
        System.out.println("Solicitud agregada a la cola.");
    }

    private static void procesarBatch() {
        System.out.println("Solicitudes en cola: " + batch.tamanio());
        batch.procesar(estudiantes, materias);
    }

    private static void agregarFacultad() {
        String codigo = leerTexto("Codigo: ");
        String nombre = leerTexto("Nombre: ");
        System.out.println(facultades.agregar(new Facultad(codigo, nombre))
                ? "Facultad registrada." : "No se pudo registrar (maximo 5 o codigo repetido).");
    }

    private static void navegarReporte() {
        System.out.println("1. Guardar reporte actual");
        System.out.println("2. Atras");
        System.out.println("3. Ver reporte actual");
        int op = leerEntero("Opcion: ");
        if (op == 1) {
            navegador.visitar(leerTexto("Nombre del reporte: "));
            System.out.println("Reporte guardado.");
        } else if (op == 2) {
            String anterior = navegador.atras();
            System.out.println(anterior == null ? "No hay reportes anteriores." : "Reporte anterior: " + anterior);
        } else if (op == 3) {
            System.out.println(navegador.reporteActual());
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException e) { System.out.println("Ingrese un numero entero valido."); }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try { return Double.parseDouble(leerTexto(mensaje).replace(',', '.')); }
            catch (NumberFormatException e) { System.out.println("Ingrese un numero valido."); }
        }
    }
}