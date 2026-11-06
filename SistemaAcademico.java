import java.util.*;

/**
 * Sistema academico en consola con:
 *  1. Registro de estudiantes
 *  2. Busqueda de estudiantes con HashMap
 *  3. Gestion de materias
 *  4. Lista enlazada propia para prerrequisitos
 *  5. Cola de espera propia para materias llenas
 *  6. Jerarquia de herencia: Persona (abstracta) -> Estudiante / Profesor
 *  7. Jerarquia de evaluaciones: Evaluacion (abstracta) -> Quiz / Parcial / ExamenFinal
 *
 * Ejecutar:  javac SistemaAcademico.java && java SistemaAcademico
 * o bien:    java SistemaAcademico.java
 */
public class SistemaAcademico {
    private static final Scanner sc = new Scanner(System.in);
    private static final RegistroEstudiantes registro = new RegistroEstudiantes();
    private static final RegistroProfesores profesores = new RegistroProfesores();
    private static final GestorMaterias gestor = new GestorMaterias();
    public static void main(String[] args) {
        cargarDatosDeEjemplo();
        int op;
        do {
            mostrarMenu();
            op = leerEntero("Opcion: ");
            System.out.println();
            switch (op) {
                case 1 -> registrarEstudiante();
                case 2 -> listarEstudiantes();
                case 3 -> buscarPorId();
                case 4 -> buscarPorNombre();
                case 5 -> registrarProfesor();
                case 6 -> listarProfesores();
                case 7 -> listarPersonas();
                case 8 -> crearMateria();
                case 9 -> listarMaterias();
                case 10 -> agregarPrerrequisito();
                case 11 -> verPrerrequisitos();
                case 12 -> asignarProfesor();
                case 13 -> inscribir();
                case 14 -> retirar();
                case 15 -> verCola();
                case 16 -> registrarNotas();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    //  MENU
    private static void mostrarMenu() {
        System.out.println("\n========== SISTEMA ACADEMICO ==========");
        System.out.println(" ESTUDIANTES");
        System.out.println("  1. Registrar estudiante");
        System.out.println("  2. Listar estudiantes");
        System.out.println("  3. Buscar estudiante por ID (HashMap)");
        System.out.println("  4. Buscar estudiante por nombre (HashMap)");
        System.out.println(" PROFESORES Y PERSONAS");
        System.out.println("  5. Registrar profesor");
        System.out.println("  6. Listar profesores");
        System.out.println("  7. Listar todas las personas (polimorfismo)");
        System.out.println(" MATERIAS");
        System.out.println("  8. Crear materia");
        System.out.println("  9. Listar materias");
        System.out.println(" 10. Agregar prerrequisito a una materia");
        System.out.println(" 11. Ver prerrequisitos de una materia");
        System.out.println(" 12. Asignar profesor a una materia");
        System.out.println(" INSCRIPCIONES Y NOTAS");
        System.out.println(" 13. Inscribir estudiante en materia");
        System.out.println(" 14. Retirar estudiante de materia");
        System.out.println(" 15. Ver cola de espera de una materia");
        System.out.println(" 16. Registrar notas (Quiz, Parcial, Examen Final)");
        System.out.println("  0. Salir");
        
    }
    // ESTUDIANTES
    private static void registrarEstudiante() {
        String id = leerTexto("ID del estudiante: ");
        if (id.isEmpty()) { System.out.println("El ID no puede estar vacio."); return; }
        if (idOcupado(id)) { System.out.println("Ya existe una persona con ese ID."); return; }
        String nombre = leerTexto("Nombre completo: ");
        if (nombre.isEmpty()) { System.out.println("El nombre no puede estar vacio."); return; }
        String email = leerEmail();
        String carrera = leerTexto("Carrera: ");
        int semestre = leerEntero("Semestre actual: ");
        if (semestre <= 0) { System.out.println("El semestre debe ser mayor que cero."); return; }
        registro.registrar(new Estudiante(id, nombre, email, carrera, semestre));
        System.out.println("Estudiante registrado correctamente.");
    }

    private static void listarEstudiantes() {
        if (registro.cantidad() == 0) { System.out.println("No hay estudiantes registrados."); return; }
        System.out.println("Estudiantes registrados (" + registro.cantidad() + "):");
        for (Estudiante e : registro.listar()) System.out.println("  " + e);
    }

    private static void buscarPorId() {
        String id = leerTexto("ID a buscar: ");
        Estudiante e = registro.buscarPorId(id);
        if (e == null) System.out.println("No se encontro un estudiante con ese ID.");
        else mostrarDetalle(e);
    }
    private static void buscarPorNombre() {
        String nombre = leerTexto("Nombre exacto a buscar: ");
        List<Estudiante> encontrados = registro.buscarPorNombre(nombre);
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron estudiantes con ese nombre.");
        } else {
            for (Estudiante e : encontrados) mostrarDetalle(e);
        }
    }
    private static void mostrarDetalle(Estudiante e) {
        e.mostrarInformacion(); // metodo sobrescrito en Estudiante
        List<String> cursando = new ArrayList<>();
        List<String> enEspera = new ArrayList<>();
        for (Materia m : gestor.todas()) {
            if (m.estaInscrito(e)) cursando.add(m.getCodigo());
            if (m.getEspera().contiene(e)) enEspera.add(m.getCodigo());
        }
        System.out.println("    Cursando: " + (cursando.isEmpty() ? "ninguna" : cursando));
        System.out.println("    En cola de espera: " + (enEspera.isEmpty() ? "ninguna" : enEspera));
    }

    // PROFESORES / PERSONAS
    private static void registrarProfesor() {
        String id = leerTexto("ID del profesor: ");
        if (id.isEmpty()) { System.out.println("El ID no puede estar vacio."); return; }
        if (idOcupado(id)) { System.out.println("Ya existe una persona con ese ID."); return; }
        String nombre = leerTexto("Nombre completo: ");
        if (nombre.isEmpty()) { System.out.println("El nombre no puede estar vacio."); return; }
        String email = leerEmail();
        String departamento = leerTexto("Departamento: ");
        double salario = leerDecimal("Salario mensual: ", 0, 1_000_000_000_000.0);
        profesores.registrar(new Profesor(id, nombre, email, departamento, salario));
        System.out.println("Profesor registrado correctamente.");
    }
    private static void listarProfesores() {
        if (profesores.listar().isEmpty()) { System.out.println("No hay profesores registrados."); return; }
        System.out.println("Profesores registrados (" + profesores.listar().size() + "):");
        for (Profesor p : profesores.listar()) {
            p.mostrarInformacion(); // metodo sobrescrito en Profesor
        }
    }
    /** Polimorfismo: una sola lista de Persona; cada objeto ejecuta SU version de mostrarInformacion(). */
    private static void listarPersonas() {
        List<Persona> todas = new ArrayList<>();
        todas.addAll(registro.listar());
        todas.addAll(profesores.listar());
        if (todas.isEmpty()) { System.out.println("No hay personas registradas."); return; }
        for (Persona p : todas) {
            p.mostrarInformacion();
            System.out.println();
        }
    }
    private static boolean idOcupado(String id) {
        return registro.buscarPorId(id) != null || profesores.buscarPorId(id) != null;
    }

    // MATERIAS
    private static void crearMateria() {
        String codigo = leerTexto("Codigo de la materia: ").toUpperCase();
        if (codigo.isEmpty()) { System.out.println("El codigo no puede estar vacio."); return; }
        String nombre = leerTexto("Nombre de la materia: ");
        int cupo = leerEntero("Cupo maximo: ");
        if (cupo <= 0) { System.out.println("El cupo debe ser mayor que cero."); return; }
        if (gestor.crear(new Materia(codigo, nombre, cupo))) {
            System.out.println("Materia creada correctamente.");
        } else {
            System.out.println("Ya existe una materia con ese codigo.");
        }
    }

    private static void listarMaterias() {
        if (gestor.todas().isEmpty()) { System.out.println("No hay materias creadas."); return; }
        System.out.println("Materias (" + gestor.todas().size() + "):");
        for (Materia m : gestor.todas()) System.out.println("  " + m);
    }

    private static void agregarPrerrequisito() {
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        Materia p = pedirMateria("Codigo del prerrequisito: ");
        if (p == null) return;
        System.out.println(gestor.agregarPrerrequisito(m, p));
    }

    private static void verPrerrequisitos() {
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        if (m.getPrerrequisitos().estaVacia()) {
            System.out.println(m.getCodigo() + " no tiene prerrequisitos.");
        } else {
            System.out.println("Prerrequisitos de " + m.getCodigo() + ": " + m.getPrerrequisitos());
        }
    }

    private static void asignarProfesor() {
        String id = leerTexto("ID del profesor: ");
        Profesor p = profesores.buscarPorId(id);
        if (p == null) { System.out.println("No existe un profesor con ese ID."); return; }
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        m.setProfesor(p);
        System.out.println(p.getNombre() + " ahora dicta " + m.getCodigo() + ".");
    }

    // ---------------------------------------------------- INSCRIPCIONES
    private static void inscribir() {
        Estudiante e = pedirEstudiante();
        if (e == null) return;
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        System.out.println(gestor.inscribir(e, m));
    }

    private static void retirar() {
        Estudiante e = pedirEstudiante();
        if (e == null) return;
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        System.out.println(gestor.retirar(e, m));
    }

    private static void verCola() {
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        System.out.println(m);
        System.out.println("Inscritos: " + m.getInscritos().size());
        for (Estudiante e : m.getInscritos()) System.out.println("   - " + e);
        if (m.getEspera().estaVacia()) {
            System.out.println("La cola de espera esta vacia.");
        } else {
            System.out.println("Cola de espera (primero en salir al inicio):");
            int pos = 1;
            for (Estudiante e : m.getEspera()) System.out.println("   " + (pos++) + ". " + e);
        }
    }

    private static void registrarNotas() {
        Estudiante e = pedirEstudiante();
        if (e == null) return;
        Materia m = pedirMateria("Codigo de la materia: ");
        if (m == null) return;
        if (!m.estaInscrito(e)) {
            System.out.println("El estudiante debe estar inscrito en " + m.getCodigo() + " para registrar sus notas.");
            return;
        }
        double quiz = leerDecimal("Nota del Quiz (0.0 - 5.0): ", 0, 5);
        double parcial = leerDecimal("Nota del Parcial (0.0 - 5.0): ", 0, 5);
        double examen = leerDecimal("Nota del Examen Final (0.0 - 5.0): ", 0, 5);

        double definitiva = e.registrarNotas(m.getCodigo(), quiz, parcial, examen);
        for (Evaluacion ev : Estudiante.crearEvaluaciones(new double[]{quiz, parcial, examen})) {
            System.out.println("   " + ev);
        }
        System.out.printf("Nota definitiva de %s en %s: %.2f -> %s%n", e.getNombre(), m.getCodigo(), definitiva,
                definitiva >= Estudiante.NOTA_MINIMA ? "APROBADA" : "REPROBADA");
        System.out.println(gestor.cerrarMateria(e, m));
    }

    // ---------------------------------------------------------- AYUDAS
    private static Estudiante pedirEstudiante() {
        String id = leerTexto("ID del estudiante: ");
        Estudiante e = registro.buscarPorId(id);
        if (e == null) System.out.println("No existe un estudiante con ese ID.");
        return e;
    }

    private static Materia pedirMateria(String mensaje) {
        String codigo = leerTexto(mensaje).toUpperCase();
        Materia m = gestor.buscar(codigo);
        if (m == null) System.out.println("No existe una materia con ese codigo.");
        return m;
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static String leerEmail() {
        while (true) {
            String email = leerTexto("Email: ");
            if (email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) return email;
            System.out.println("Email invalido (ejemplo: nombre@correo.com).");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException ex) {
                System.out.println("Por favor ingresa un numero entero valido.");
            }
        }
    }

    private static double leerDecimal(String mensaje, double min, double max) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(linea);
                if (valor >= min && valor <= max) return valor;
                System.out.println("Valor fuera del rango permitido.");
            } catch (NumberFormatException ex) {
                System.out.println("Ingresa un numero valido (ejemplo: 3.5).");
            }
        }
    }

    private static void cargarDatosDeEjemplo() {
        registro.registrar(new Estudiante("1001", "Ana Perez", "ana.perez@correo.com", "Ingenieria de Sistemas", 3));
        registro.registrar(new Estudiante("1002", "Luis Gomez", "luis.gomez@correo.com", "Ingenieria de Sistemas", 3));
        registro.registrar(new Estudiante("1003", "Maria Rojas", "maria.rojas@correo.com", "Matematicas", 2));
        registro.registrar(new Estudiante("1004", "Carlos Diaz", "carlos.diaz@correo.com", "Ingenieria de Sistemas", 2));

        profesores.registrar(new Profesor("P01", "Jorge Salazar", "jorge.salazar@uni.edu", "Matematicas", 5_500_000));
        profesores.registrar(new Profesor("P02", "Laura Vargas", "laura.vargas@uni.edu", "Sistemas", 6_000_000));

        gestor.crear(new Materia("MAT101", "Calculo I", 2));
        gestor.crear(new Materia("MAT102", "Calculo II", 2));
        gestor.crear(new Materia("PRO101", "Programacion I", 2));
        gestor.crear(new Materia("PRO201", "Estructuras de Datos", 2));

        gestor.agregarPrerrequisito(gestor.buscar("MAT102"), gestor.buscar("MAT101"));
        gestor.agregarPrerrequisito(gestor.buscar("PRO201"), gestor.buscar("PRO101"));

        gestor.buscar("MAT101").setProfesor(profesores.buscarPorId("P01"));
        gestor.buscar("MAT102").setProfesor(profesores.buscarPorId("P01"));
        gestor.buscar("PRO101").setProfesor(profesores.buscarPorId("P02"));
        gestor.buscar("PRO201").setProfesor(profesores.buscarPorId("P02"));

        // Historial inicial (notas: Quiz, Parcial, Examen Final)
        registro.buscarPorId("1001").registrarNotas("PRO101", 4.0, 3.8, 4.2);
        registro.buscarPorId("1002").registrarNotas("PRO101", 3.5, 3.0, 3.4);
        registro.buscarPorId("1003").registrarNotas("PRO101", 4.5, 4.0, 4.8);
        registro.buscarPorId("1004").registrarNotas("MAT101", 2.0, 2.5, 2.8); // reprobada

        System.out.println("(Se cargaron datos de ejemplo: 4 estudiantes, 2 profesores y 4 materias)");
    }
}

// =====================================================================
// JERARQUIA 1: PERSONA (abstracta) -> ESTUDIANTE / PROFESOR
// =====================================================================
abstract class Persona {
    private final String id;
    private final String nombre;
    private final String email;

    Persona(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    /** Cada subclase indica que tipo de persona es. */
    public abstract String getRol();

    /** Informacion comun; las subclases la sobrescriben y la amplian. */
    public void mostrarInformacion() {
        System.out.println("  [" + getRol() + "] " + nombre + " (ID " + id + ")");
        System.out.println("    Email: " + email);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre;
    }
}

class Estudiante extends Persona {
    static final int NUM_EVALUACIONES = 3;   // Quiz, Parcial, Examen Final
    static final double NOTA_MINIMA = 3.0;   // escala 0.0 a 5.0

    private final String carrera;
    private int semestre;
    // notas[i] = {quiz, parcial, examenFinal} de la materia historialMaterias.get(i)
    private double[][] notas = new double[0][NUM_EVALUACIONES];
    private final List<String> historialMaterias = new ArrayList<>();
    private final Set<String> aprobadas = new HashSet<>();

    Estudiante(String id, String nombre, String email, String carrera, int semestre) {
        super(id, nombre, email);
        this.carrera = carrera;
        this.semestre = semestre;
    }

    public String getCarrera() { return carrera; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public Set<String> getAprobadas() { return aprobadas; }
    public List<String> getHistorialMaterias() { return Collections.unmodifiableList(historialMaterias); }

    @Override
    public String getRol() { return "Estudiante"; }

    /** Crea las 3 evaluaciones (polimorfismo: todas son Evaluacion). */
    static Evaluacion[] crearEvaluaciones(double[] fila) {
        return new Evaluacion[]{
                new Quiz(fila[0]),
                new Parcial(fila[1]),
                new ExamenFinal(fila[2])
        };
    }

    /** Nota definitiva = suma de los aportes ponderados de cada evaluacion. */
    static double definitiva(double[] fila) {
        double total = 0;
        for (Evaluacion ev : crearEvaluaciones(fila)) total += ev.aporte();
        return total;
    }

    /** Guarda las notas en la matriz y en el historial. Devuelve la nota definitiva. */
    double registrarNotas(String codigoMateria, double quiz, double parcial, double examenFinal) {
        double[] fila = {quiz, parcial, examenFinal};
        double definitiva = definitiva(fila); // valida el rango de las notas
        notas = Arrays.copyOf(notas, notas.length + 1);
        notas[notas.length - 1] = fila;
        historialMaterias.add(codigoMateria);
        if (definitiva >= NOTA_MINIMA) aprobadas.add(codigoMateria);
        return definitiva;
    }

    double promedioGeneral() {
        if (notas.length == 0) return 0;
        double suma = 0;
        for (double[] fila : notas) suma += definitiva(fila);
        return suma / notas.length;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("    Carrera: " + carrera + " | Semestre: " + semestre);
        System.out.println("    Materias aprobadas: " + (aprobadas.isEmpty() ? "ninguna" : aprobadas));
        if (historialMaterias.isEmpty()) {
            System.out.println("    Historial: sin materias cursadas");
            return;
        }
        System.out.println("    Historial (Quiz / Parcial / Final -> Definitiva):");
        for (int i = 0; i < historialMaterias.size(); i++) {
            double def = definitiva(notas[i]);
            System.out.printf("      %s: %.1f / %.1f / %.1f -> %.2f %s%n",
                    historialMaterias.get(i), notas[i][0], notas[i][1], notas[i][2], def,
                    def >= NOTA_MINIMA ? "(aprobada)" : "(reprobada)");
        }
        System.out.printf("    Promedio general: %.2f%n", promedioGeneral());
    }

    @Override
    public String toString() {
        return super.toString() + " - " + carrera + " (semestre " + semestre + ")";
    }
}

class Profesor extends Persona {
    private final String departamento;
    private final double salario;

    Profesor(String id, String nombre, String email, String departamento, double salario) {
        super(id, nombre, email);
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }

    @Override
    public String getRol() { return "Profesor"; }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("    Departamento: " + departamento);
        System.out.printf("    Salario: %,.0f%n", salario);
    }

    @Override
    public String toString() {
        return super.toString() + " - Dpto. " + departamento;
    }
}

// =====================================================================
// JERARQUIA 2: EVALUACION (base) -> QUIZ / PARCIAL / EXAMEN FINAL
// =====================================================================
abstract class Evaluacion {
    private final String nombre;
    private final double peso;   // fraccion de la nota definitiva (0.0 a 1.0)
    private final double nota;   // 0.0 a 5.0

    Evaluacion(String nombre, double peso, double nota) {
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        }
        this.nombre = nombre;
        this.peso = peso;
        this.nota = nota;
    }

    public String getNombre() { return nombre; }
    public double getPeso() { return peso; }
    public double getNota() { return nota; }

    /** Contribucion de esta evaluacion a la nota definitiva. */
    public double aporte() { return nota * peso; }

    public abstract String descripcion();

    @Override
    public String toString() {
        return String.format("%s (%d%%): %.1f -> aporta %.2f", nombre, Math.round(peso * 100), nota, aporte());
    }
}

class Quiz extends Evaluacion {
    Quiz(double nota) { super("Quiz", 0.20, nota); }

    @Override
    public String descripcion() { return "Evaluacion corta de seguimiento"; }
}

class Parcial extends Evaluacion {
    Parcial(double nota) { super("Parcial", 0.30, nota); }

    @Override
    public String descripcion() { return "Evaluacion de mitad de periodo"; }
}

class ExamenFinal extends Evaluacion {
    ExamenFinal(double nota) { super("Examen Final", 0.50, nota); }

    @Override
    public String descripcion() { return "Evaluacion global de cierre del curso"; }
}

// =====================================================================
// REGISTROS Y BUSQUEDA CON HASHMAP
// =====================================================================
class RegistroEstudiantes {
    private final List<Estudiante> lista = new ArrayList<>();
    // Indice por ID: busqueda en O(1)
    private final Map<String, Estudiante> porId = new HashMap<>();
    // Indice por nombre (en minusculas): puede haber homonimos, por eso es una lista
    private final Map<String, List<Estudiante>> porNombre = new HashMap<>();

    boolean registrar(Estudiante e) {
        if (porId.containsKey(e.getId())) return false;
        lista.add(e);
        porId.put(e.getId(), e);
        porNombre.computeIfAbsent(e.getNombre().toLowerCase(), k -> new ArrayList<>()).add(e);
        return true;
    }

    Estudiante buscarPorId(String id) {
        return porId.get(id);
    }

    List<Estudiante> buscarPorNombre(String nombre) {
        return porNombre.getOrDefault(nombre.toLowerCase(), Collections.emptyList());
    }

    List<Estudiante> listar() { return Collections.unmodifiableList(lista); }
    int cantidad() { return lista.size(); }
}

class RegistroProfesores {
    private final Map<String, Profesor> porId = new LinkedHashMap<>();

    boolean registrar(Profesor p) {
        if (porId.containsKey(p.getId())) return false;
        porId.put(p.getId(), p);
        return true;
    }

    Profesor buscarPorId(String id) { return porId.get(id); }
    Collection<Profesor> listar() { return porId.values(); }
}

// =====================================================================
// LISTA ENLAZADA (usada para los prerrequisitos)
// =====================================================================
class ListaEnlazada<T> implements Iterable<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> cabeza;
    private int tamano;

    void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    boolean contiene(T dato) {
        for (T d : this) if (d.equals(dato)) return true;
        return false;
    }

    boolean eliminar(T dato) {
        if (cabeza == null) return false;
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamano--;
            return true;
        }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(dato)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    int tamano() { return tamano; }
    boolean estaVacia() { return tamano == 0; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo<T> actual = cabeza;
            public boolean hasNext() { return actual != null; }
            public T next() {
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T d : this) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(d instanceof Materia ? ((Materia) d).getCodigo() : String.valueOf(d));
        }
        return sb.length() == 0 ? "(vacia)" : sb.toString();
    }
}

// =====================================================================
// COLA DE ESPERA (FIFO con nodos enlazados)
// =====================================================================
class ColaEspera<T> implements Iterable<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> frente, fin;
    private int tamano;

    void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    T desencolar() {
        if (frente == null) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamano--;
        return dato;
    }

    T verFrente() { return frente == null ? null : frente.dato; }

    boolean contiene(T dato) {
        for (T d : this) if (d.equals(dato)) return true;
        return false;
    }

    /** Elimina a alguien de cualquier posicion de la cola (por ejemplo, si desiste). */
    boolean eliminar(T dato) {
        Nodo<T> previo = null, actual = frente;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                if (previo == null) frente = actual.siguiente;
                else previo.siguiente = actual.siguiente;
                if (actual == fin) fin = previo;
                tamano--;
                return true;
            }
            previo = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    int tamano() { return tamano; }
    boolean estaVacia() { return tamano == 0; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo<T> actual = frente;
            public boolean hasNext() { return actual != null; }
            public T next() {
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}

// =====================================================================
// GESTION DE MATERIAS
// =====================================================================
class Materia {
    private final String codigo;
    private final String nombre;
    private final int cupo;
    private Profesor profesor;
    private final ListaEnlazada<Materia> prerrequisitos = new ListaEnlazada<>();
    private final List<Estudiante> inscritos = new ArrayList<>();
    private final ColaEspera<Estudiante> espera = new ColaEspera<>();

    Materia(String codigo, String nombre, int cupo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cupo = cupo;
    }

    String getCodigo() { return codigo; }
    String getNombre() { return nombre; }
    int getCupo() { return cupo; }
    Profesor getProfesor() { return profesor; }
    void setProfesor(Profesor profesor) { this.profesor = profesor; }
    ListaEnlazada<Materia> getPrerrequisitos() { return prerrequisitos; }
    List<Estudiante> getInscritos() { return inscritos; }
    ColaEspera<Estudiante> getEspera() { return espera; }

    boolean estaLlena() { return inscritos.size() >= cupo; }
    boolean estaInscrito(Estudiante e) { return inscritos.contains(e); }

    /** Indica si esta materia depende (directa o indirectamente) de otra. */
    boolean dependeDe(Materia otra) {
        for (Materia p : prerrequisitos) {
            if (p == otra || p.dependeDe(otra)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " | cupo " + inscritos.size() + "/" + cupo
                + " | en espera: " + espera.tamano()
                + (profesor != null ? " | Prof: " + profesor.getNombre() : "")
                + (estaLlena() ? " (LLENA)" : "");
    }
}

class GestorMaterias {
    private final Map<String, Materia> materias = new LinkedHashMap<>();

    boolean crear(Materia m) {
        if (materias.containsKey(m.getCodigo())) return false;
        materias.put(m.getCodigo(), m);
        return true;
    }

    Materia buscar(String codigo) { return materias.get(codigo); }
    Collection<Materia> todas() { return materias.values(); }

    String agregarPrerrequisito(Materia materia, Materia prerreq) {
        if (materia == prerreq) return "Una materia no puede ser prerrequisito de si misma.";
        if (materia.getPrerrequisitos().contiene(prerreq)) return "Ese prerrequisito ya estaba registrado.";
        if (prerreq.dependeDe(materia)) {
            return "No se puede: crearia un ciclo (" + prerreq.getCodigo()
                    + " ya depende de " + materia.getCodigo() + ").";
        }
        materia.getPrerrequisitos().agregar(prerreq);
        return "Prerrequisito agregado: " + prerreq.getCodigo() + " -> " + materia.getCodigo();
    }

    String inscribir(Estudiante e, Materia m) {
        if (m.estaInscrito(e)) return e.getNombre() + " ya esta inscrito en " + m.getCodigo() + ".";
        if (m.getEspera().contiene(e)) return e.getNombre() + " ya esta en la cola de espera de " + m.getCodigo() + ".";
        if (e.getAprobadas().contains(m.getCodigo())) return e.getNombre() + " ya aprobo " + m.getCodigo() + ".";

        // Verificar prerrequisitos recorriendo la lista enlazada
        List<String> faltantes = new ArrayList<>();
        for (Materia p : m.getPrerrequisitos()) {
            if (!e.getAprobadas().contains(p.getCodigo())) faltantes.add(p.getCodigo());
        }
        if (!faltantes.isEmpty()) {
            return "No se puede inscribir: faltan prerrequisitos " + faltantes + ".";
        }

        if (!m.estaLlena()) {
            m.getInscritos().add(e);
            return e.getNombre() + " inscrito en " + m.getCodigo() + ".";
        }
        m.getEspera().encolar(e);
        return m.getCodigo() + " esta llena. " + e.getNombre()
                + " fue agregado a la cola de espera en la posicion " + m.getEspera().tamano() + ".";
    }

    String retirar(Estudiante e, Materia m) {
        if (m.getInscritos().remove(e)) {
            return e.getNombre() + " fue retirado de " + m.getCodigo() + "." + promoverDeCola(m);
        }
        if (m.getEspera().eliminar(e)) {
            return e.getNombre() + " fue removido de la cola de espera de " + m.getCodigo() + ".";
        }
        return e.getNombre() + " no esta inscrito ni en espera en " + m.getCodigo() + ".";
    }

    /** Se usa al terminar el curso (cuando se registran las notas): libera el cupo. */
    String cerrarMateria(Estudiante e, Materia m) {
        m.getInscritos().remove(e);
        return "Se libero el cupo de " + e.getNombre() + " en " + m.getCodigo() + "." + promoverDeCola(m);
    }

    /** Mientras haya cupo, pasa al siguiente de la cola de espera. */
    private String promoverDeCola(Materia m) {
        StringBuilder sb = new StringBuilder();
        while (!m.estaLlena() && !m.getEspera().estaVacia()) {
            Estudiante siguiente = m.getEspera().desencolar();
            m.getInscritos().add(siguiente);
            sb.append("\nDe la cola de espera ingreso: ").append(siguiente.getNombre()).append(".");
        }
        return sb.toString();
    }
}
