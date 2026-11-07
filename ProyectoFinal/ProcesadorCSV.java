import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class ProcesadorCSV {
    private Queue<Solicitud> colaSolicitudes = new LinkedList<>();

    public void cargarCSV(String archivo) {
        colaSolicitudes.clear();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    colaSolicitudes.offer(new Solicitud(
                        datos[0].trim(), datos[1].trim()
                    ));
                } else {
                    System.out.println("Linea CSV ignorada por formato incorrecto: " + linea);
                }
            }
            System.out.println("Solicitudes cargadas correctamente: " + colaSolicitudes.size());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public void procesarSolicitudes(HashMap<String, Estudiante> estudiantes,
                                    HashMap<String, Materia> materias) {
        int exitosas = 0;
        int fallidas = 0;

        while (!colaSolicitudes.isEmpty()) {
            Solicitud solicitud = colaSolicitudes.poll();
            Estudiante estudiante = estudiantes.get(solicitud.getIdEstudiante());
            Materia materia = materias.get(solicitud.getCodigoMateria());

            if (estudiante == null) {
                System.out.println("Fallida: estudiante no encontrado (" +
                                   solicitud.getIdEstudiante() + ").");
                fallidas++;
            } else if (materia == null) {
                System.out.println("Fallida: materia no encontrada (" +
                                   solicitud.getCodigoMateria() + ").");
                fallidas++;
            } else if (materia.inscribir(estudiante.getId())) {
                exitosas++;
            } else {
                // La solicitud queda en la cola de espera de la materia.
                fallidas++;
            }
        }

        System.out.println("\\n===== RESUMEN CSV =====");
        System.out.println("Exitosas: " + exitosas);
        System.out.println("Fallidas o en espera: " + fallidas);
    }
}
