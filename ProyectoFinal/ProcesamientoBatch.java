package ProyectoFinal;
import java.util.LinkedList;
import java.util.Queue;
public class ProcesamientoBatch {
    private Queue<SolicitudInscripcion> solicitudes = new LinkedList<>();
    public void agregarSolicitud(SolicitudInscripcion s) {
         if (s != null) solicitudes.offer(s); 
        }
    public int tamanio() { 
        return solicitudes.size(); 
    }
    public void procesar(RegistroEstudiantes estudiantes, GestorMaterias materias) {
        int exitosas = 0, fallidas = 0;
        while (!solicitudes.isEmpty()) {
            SolicitudInscripcion s = solicitudes.poll();
            Estudiante e = estudiantes.buscarPorId(s.getIdEstudiante());
            if (e != null && materias.inscribir(s.getCodigoMateria(), e)) {
                System.out.println(s + " | Exitosa"); exitosas++;
            } else {
                System.out.println(s + " | Fallida"); fallidas++;
            }
        }
        System.out.println("Resumen: exitosas=" + exitosas + ", fallidas=" + fallidas);
    }
}