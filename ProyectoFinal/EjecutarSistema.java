import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Stack;

public class EjecutarSistema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArreglosAcademicos arreglo = new ArreglosAcademicos();
        MatricesAcademicas matriz = new MatricesAcademicas();
        ProcesadorCSV procesador = new ProcesadorCSV();

        HashMap<String, Estudiante> estudiantes = new HashMap<>();
        HashMap<String, Materia> materias = new HashMap<>();
        TreeMap<String, String> aulas = new TreeMap<>();

        // Estas pilas permiten deshacer y rehacer registros de estudiantes.
        Stack<Estudiante> pilaDeshacer = new Stack<>();
        Stack<Estudiante> pilaRehacer = new Stack<>();

        materias.put("MAT101", new Materia("MAT101", "Matematicas", 3));
        materias.put("PRO101", new Materia("PRO101", "Programacion", 2));

        int opcion;
        do {
            System.out.println("\\n==================================");
            System.out.println(" SISTEMA DE GESTION UNIVERSITARIA");
            System.out.println("==================================");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante");
            System.out.println("3. Listar estudiantes");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Procesar archivo CSV");
            System.out.println("6. Registrar aula");
            System.out.println("7. Mostrar aulas");
            System.out.println("8. Reservar horario");
            System.out.println("9. Consultar horario");
            System.out.println("10. Liberar horario");
            System.out.println("11. Registrar distancia");
            System.out.println("12. Mostrar matriz de distancias");
            System.out.println("13. Deshacer ultimo registro");
            System.out.println("14. Rehacer registro");
            System.out.println("0. Salir");
            System.out.print("\\nSeleccione una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un numero: ");
                sc.nextLine();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.println("\\nREGISTRAR ESTUDIANTE");
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Semestre: ");
                    int semestre = sc.nextInt();
                    sc.nextLine();

                    if (estudiantes.containsKey(id)) {
                        System.out.println("El ID ya existe.");
                        break;
                    }
                    if (arreglo.getCantidad() >= 100) {
                        System.out.println("El arreglo de estudiantes esta lleno.");
                        break;
                    }

                    Estudiante nuevo = new Estudiante(nombre, id, email, semestre);
                    arreglo.registrar(nuevo);
                    estudiantes.put(id, nuevo);
                    pilaDeshacer.push(nuevo);
                    pilaRehacer.clear();
                    break;
                }
                case 2: {
                    System.out.print("Ingrese el ID: ");
                    String buscarId = sc.nextLine();
                    Estudiante encontrado = estudiantes.get(buscarId);
                    if (encontrado != null) encontrado.mostrarInformacion();
                    else System.out.println("Estudiante no encontrado.");
                    break;
                }
                case 3:
                    arreglo.listar();
                    break;
                case 4: {
                    System.out.print("ID del estudiante: ");
                    String eliminarId = sc.nextLine();
                    Estudiante eliminado = estudiantes.remove(eliminarId);
                    if (eliminado != null) {
                        arreglo.eliminar(eliminarId);
                        System.out.println("Estudiante eliminado. Esta eliminacion no se puede deshacer en esta version.");
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    break;
                }
                case 5: {
                    System.out.print("Nombre o ruta del archivo CSV: ");
                    String archivo = sc.nextLine();
                    procesador.cargarCSV(archivo);
                    procesador.procesarSolicitudes(estudiantes, materias);
                    break;
                }
                case 6: {
                    System.out.print("Nombre del aula: ");
                    String nombreAula = sc.nextLine();
                    System.out.print("Capacidad: ");
                    String capacidad = sc.nextLine();
                    aulas.put(nombreAula, capacidad);
                    System.out.println("Aula registrada.");
                    break;
                }
                case 7:
                    System.out.println("\\nAULAS ORDENADAS");
                    for (String aula : aulas.keySet()) {
                        System.out.println(aula + " - Capacidad: " + aulas.get(aula));
                    }
                    break;
                case 8: {
                    System.out.print("Dia (0=Domingo, 1=Lunes...6=Sabado): ");
                    int dia = sc.nextInt();
                    System.out.print("Hora inicial: ");
                    int hora = sc.nextInt();
                    System.out.print("Duracion: ");
                    int duracion = sc.nextInt();
                    sc.nextLine();
                    matriz.reservar(dia, hora, duracion);
                    break;
                }
                case 9: {
                    System.out.print("Dia: ");
                    int dia = sc.nextInt();
                    System.out.print("Hora: ");
                    int hora = sc.nextInt();
                    sc.nextLine();
                    matriz.consultar(dia, hora);
                    break;
                }
                case 10: {
                    System.out.print("Dia: ");
                    int dia = sc.nextInt();
                    System.out.print("Hora: ");
                    int hora = sc.nextInt();
                    System.out.print("Duracion: ");
                    int duracion = sc.nextInt();
                    sc.nextLine();
                    matriz.liberar(dia, hora, duracion);
                    break;
                }
                case 11: {
                    System.out.print("Edificio origen (0-4): ");
                    int origen = sc.nextInt();
                    System.out.print("Edificio destino (0-4): ");
                    int destino = sc.nextInt();
                    System.out.print("Distancia en metros: ");
                    int metros = sc.nextInt();
                    sc.nextLine();
                    matriz.registrarDistancia(origen, destino, metros);
                    break;
                }
                case 12:
                    matriz.mostrarDistancias();
                    break;
                case 13:
                    if (!pilaDeshacer.isEmpty()) {
                        Estudiante estudiante = pilaDeshacer.pop();
                        arreglo.eliminar(estudiante.getId());
                        estudiantes.remove(estudiante.getId());
                        pilaRehacer.push(estudiante);
                        System.out.println("Registro deshecho.");
                    } else {
                        System.out.println("No hay registros para deshacer.");
                    }
                    break;
                case 14:
                    if (!pilaRehacer.isEmpty()) {
                        Estudiante estudiante = pilaRehacer.pop();
                        if (arreglo.getCantidad() < 100 &&
                            !estudiantes.containsKey(estudiante.getId())) {
                            arreglo.registrar(estudiante);
                            estudiantes.put(estudiante.getId(), estudiante);
                            pilaDeshacer.push(estudiante);
                            System.out.println("Registro recuperado.");
                        } else {
                            System.out.println("No se puede rehacer: ID existente o arreglo lleno.");
                        }
                    } else {
                        System.out.println("No hay registros para rehacer.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
